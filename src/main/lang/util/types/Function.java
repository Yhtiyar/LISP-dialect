package main.lang.util.types;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.util.Value;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 08.10.2020
 */
public abstract class Function implements Expression {
    //private static FunctionCall functionCall;

    public abstract FunctionCall getFunctionCall(ArrayList<Expression> args);

    @Override
    public Value evaluate(Scope scope) {
        throw new IllegalArgumentException("Called function incorrectly");
    }

}
