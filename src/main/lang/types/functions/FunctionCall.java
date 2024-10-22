package main.lang.types.functions;

import main.lang.Expression;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 08.10.2020
 */
public abstract class FunctionCall implements Expression {

    protected ArrayList<Expression> args;

    public FunctionCall(ArrayList<Expression> args) {
        this.args = args;
    }
    protected abstract String getName();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(').append(getName());

        for (var arg : args)
            sb.append(' ').append(arg.toString());

        return sb.append(')').toString();
    }
}
