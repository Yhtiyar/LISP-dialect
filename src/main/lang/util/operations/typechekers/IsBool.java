package main.lang.util.operations.typechekers;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.Bool;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 14.10.2020
 */
public class IsBool extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "bool?";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() != 1)
                    throw new IllegalArgumentException("wrong arg count : bool?");
                return new Bool(args.get(0).evaluate(scope).isBoolean());
            }
        };
    }
}
