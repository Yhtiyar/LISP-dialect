package main.lang.util.operations.loops;

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
public class While extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "while";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() != 2)
                    throw new IllegalArgumentException("Wrong arg count : while");
                Value ans = null;
                while (((Bool)args.get(0).evaluate(scope)).getInnerValue()) {
                    ans = args.get(1).evaluate(scope);
                }
                return ans;
            }
        };
    }
}
