/*
 * Copyright (c) 2009 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.serialization;

import com.mysema.query.types.AbstractVisitor;
import com.mysema.query.types.alias.ASimple;
import com.mysema.query.types.alias.AToPath;
import com.mysema.query.types.custom.Custom;
import com.mysema.query.types.expr.EArrayConstructor;
import com.mysema.query.types.expr.EConstant;
import com.mysema.query.types.expr.EConstructor;
import com.mysema.query.types.expr.Expr;
import com.mysema.query.types.operation.Operation;
import com.mysema.query.types.path.Path;
import com.mysema.query.types.quant.Quant;

/**
 * ToStringVisitor provides
 *
 * @author tiwe
 * @version $Id$
 */
public class ToStringVisitor extends AbstractVisitor<ToStringVisitor>{

    private static OperationPatterns ops = new OperationPatterns(){{
        // TODO
    }};
    
    private String toString = "?";
    
    public String toString(){
        return toString;
    }
    
    @Override
    protected void visit(ASimple<?> expr) {
        toString = expr.getFrom() + " to " + expr.getTo();
    }
    
    @Override
    protected void visit(AToPath expr) {
        toString = expr.getFrom() + " to " + expr.getTo();        
    }
    
    @Override
    protected void visit(Custom<?> expr) {
        toString = String.format(expr.getPattern(), expr.getArgs());
        
    }
    
    protected void visit(EArrayConstructor<?> e){
        StringBuilder builder = new StringBuilder("[");
        for (int i=0; i < e.getArgs().size(); i++){
            if (i > 0) builder.append(", ");
            builder.append(e.getArg(i));
        }
        builder.append("]");
        toString = builder.toString();
    }
    
    protected void visit(EConstant<?> e){
        toString = e.getConstant().toString();
    }

    protected void visit(EConstructor<?> e){
        StringBuilder builder = new StringBuilder();
        builder.append("new ").append(e.getType().getSimpleName()).append("(");
        for (int i=0; i < e.getArgs().size(); i++){
            if (i > 0) builder.append(", ");
            builder.append(e.getArg(i));
        }
        builder.append(")");
        toString = builder.toString();
    }

    protected void visit(Operation<?,?> o){
        String pattern = ops.getPattern(o.getOperator());
        if (pattern != null){
            toString =  String.format(pattern, o.getArgs().toArray());
        }else{
            toString = "unknown operation with args " + o.getArgs();
        }
    }

    protected void visit(Path<?> p){
        Path<?> parent = p.getMetadata().getParent();
        Expr<?> expr = p.getMetadata().getExpression();
        if (parent != null){
            String pattern = ops.getPattern(p.getMetadata().getPathType());
            if (pattern != null){
                toString = String.format(pattern, parent, expr);    
            }             
        }else if (expr != null){
            toString =  expr.toString();
        }
    }

	@Override
	protected void visit(Quant<?> q) {
		// TODO Auto-generated method stub
		
	}

}
