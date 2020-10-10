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
public class Subtract extends Function {

    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "-";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() < 1)
                    throw new IllegalArgumentException("Wrong number of args expected at least 1");
                Number ans = new Number(0.d);
                for (var expr : args) {
                    Value res = expr.evaluate(scope);
                    if (!(res instanceof Number))
                        throw new IllegalArgumentException("Cannot cast :" + expr + " to Number");
                    ans.add(-((Number) res).getInnerValue());
                }
                return ans;
            }
        };
    }

}
