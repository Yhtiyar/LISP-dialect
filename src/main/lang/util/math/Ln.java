package main.lang.util.math;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.Number;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;
import main.lang.types.*;
import java.util.ArrayList;

/**
 * @author Yhtyyar created on 10.10.2020
 */
public class Ln extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "ln";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() != 1)
                    throw new IllegalArgumentException("Incorrect arg count: ln");
                Number n = (Number) args.get(0).evaluate(scope);

                return new Number(Math.log(n.getInnerValue()));
            }
        };
    }
}
