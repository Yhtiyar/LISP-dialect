package main.lang.util.operations;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.Bool;
import main.lang.types.StringValue;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 15.10.2020
 */
public class Assert extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "assert";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() < 1 || args.size() > 2)
                    throw new IllegalArgumentException("wrong arg count: arg");
                Boolean b = ((Bool)args.get(0).evaluate(scope)).getInnerValue();
                if (b)
                    return new Bool(true);
                if (args.size() == 1) {
                    throw new IllegalArgumentException("Assert failed :" + args.get(0));
                }
                throw new IllegalArgumentException(((StringValue)args.get(1).evaluate(scope)).getInnerValue());
            }
        };
    }
}
