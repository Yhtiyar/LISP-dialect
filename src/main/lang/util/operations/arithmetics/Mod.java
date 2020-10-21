package main.lang.util.operations;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.Number;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 15.10.2020
 */
public class Mod extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "mod";
            }
            @Override
            public Value evaluate(Scope scope) {
                if (args.size() != 2)
                    throw new IllegalArgumentException("Wrong arg count: mod");
                Number n1 = (Number) args.get(0).evaluate(scope);
                Number n2 = (Number) args.get(1).evaluate(scope);
                if (!n1.isInt() || !n2.isInt())
                    throw new IllegalArgumentException(n1 + "or" + n2 + "are with floating point");

                return new Number(n1.intValue()%n2.intValue());
            }
        };
    }
}
