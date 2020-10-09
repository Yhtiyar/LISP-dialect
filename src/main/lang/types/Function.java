package main.lang.types;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 08.10.2020
 */
public abstract class Function extends Value {
    //private static FunctionCall functionCall;

    public abstract FunctionCall getFunctionCall(ArrayList<Expression> args);

    @Override
    public Value evaluate(Scope scope) {
        return this;
    }

    @Override
    public String toString() {
        return "{Function : " + getFunctionCall(new ArrayList<>()).getName() + "}";
    }

}
