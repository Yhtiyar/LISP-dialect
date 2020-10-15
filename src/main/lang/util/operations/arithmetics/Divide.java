package main.lang.util.operations.arithmetics;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;
import main.lang.types.Number;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 09.10.2020
 */
public class Divide extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "/";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() < 1)
                    throw new IllegalArgumentException("Wrong number of args expected at least 1");
                Number ans  = new Number(((Number) args.get(0).evaluate(scope)) .getInnerValue());
                for (int i = 1; i < args.size(); i++) {
                    Value res = args.get(i).evaluate(scope);
                    if (!(res instanceof Number))
                        throw new IllegalArgumentException("Cannot cast :" + args.get(i) + " to Number");
                    ans.divide(((Number) res).getInnerValue());
                }
                return ans;
            }
        };
    }
}
