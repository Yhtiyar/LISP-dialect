package main.lang.types.functions;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 10.10.2020
 */
public class PrivateFunctionCall extends FunctionCall {
    private String funcName;
    public PrivateFunctionCall(ArrayList<Expression> args, String funcName) {
        super(args);
        this.funcName = funcName;
    }

    @Override
    protected String getName() {
        return funcName;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public Value evaluate(Scope scope) {
        Function f = scope.getFunction(funcName);
        System.err.println("----DEBUG-INFO--- : getting function:"+funcName);
        return f.getFunctionCall(args).evaluate(scope);
    }
}
