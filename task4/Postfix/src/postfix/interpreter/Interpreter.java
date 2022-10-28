/* *******************************************************************
 * Copyright (c) 2021 Universidade Federal de Pernambuco (UFPE).
 * 
 * This file is part of the Compilers course at UFPE.
 * 
 * During the 1970s and 1980s, Hewlett-Packard used RPN in all 
 * of their desktop and hand-held calculators, and continued to 
 * use it in some models into the 2020s. In computer science, 
 * reverse Polish notation is used in stack-oriented programming languages 
 * such as Forth, STOIC, PostScript, RPL and Joy.
 *  
 * Contributors: 
 *     Henrique Rebelo      initial design and implementation 
 *     http://www.cin.ufpe.br/~hemr/
 * ******************************************************************/

package postfix.interpreter;

import postfix.ast.Expr;

import java.util.HashMap;

/**
 * @author Henrique Rebelo
 */
public class Interpreter implements Expr.Visitor<Integer> {


	public HashMap<String, String> env;
	public Interpreter(HashMap<String, String> envi){
		env = envi;
	}


	public int interp(Expr expression) throws InterpreterException{
		return  evaluate(expression);
	}

	@Override
	public Integer visitNumberExpr(Expr.Number expr) {
		return Integer.parseInt(expr.value);
	}

	@Override
	public Integer visitVariableExpr(Expr.Variable expr) throws InterpreterException {
		String var = env.get(expr.value);
		if(var == null){
			throw new InterpreterException(expr.value + " cannot be resolved");
		}
		return Integer.parseInt(env.get(expr.value));
	}

	@Override
	public Integer visitBinopExpr(Expr.Binop expr) throws InterpreterException {
		int left = evaluate(expr.left);
		int right = evaluate(expr.right);
		int result = 0;

		switch (expr.operator.type) {
			case PLUS:
				result = left + right;
				break;
			case MINUS:
				result = left - right;
				break;
			case SLASH:
				result = left / right;
				break;
			case STAR:
				result = left * right;
				break;
			default:
				break;
		}

		return result;
	}
	private int evaluate(Expr expr) throws InterpreterException{
		return expr.accept(this);
	}
}
