package main.lang.util.operations.logical;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.Bool;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 13.10.2020
 */
public class Not extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "not";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() != 1)
                    throw new IllegalArgumentException("Wrong arg count not");
                Boolean ans = ((Bool) args.get(0).evaluate(scope)).getInnerValue();
                return new Bool(!ans);
            }
        };
    }
}
