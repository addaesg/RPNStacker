package postfix.ast;

import java.util.Stack;

import postfix.ast.Expr.Binop;
import postfix.ast.Expr.Number;
import postfix.interpreter.InterpreterException;

public class AstPrinter implements Expr.Visitor<String>{

	public String print(Expr expr) throws InterpreterException {
		return expr.accept(this);
	}

	@Override
	public String visitNumberExpr(Number expr) {
		return expr.value;
	}

	@Override
	public String visitBinopExpr(Binop expr) throws InterpreterException {
		return parenthesizePreOrder(expr.operator.lexeme,
				expr.left, expr.right);
	}

	public String visitVariableExpr(Expr.Variable expr){
			return expr.value;
	}

	// -------------------------------------------------------------
	// HELPERS METHODS
	// -------------------------------------------------------------

	private String parenthesizePreOrder(String name, Expr... exprs) throws InterpreterException {
		StringBuffer buffer = new StringBuffer();

		buffer.append("(").append(name);
		for (Expr expr : exprs) {
			buffer.append(" ");
			buffer.append(expr.accept(this));
		}
		buffer.append(")");

		return buffer.toString();
	}
}
