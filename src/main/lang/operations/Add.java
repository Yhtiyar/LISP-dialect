package main.lang.operations;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.util.Value;
import main.lang.util.types.Function;
import main.lang.util.types.FunctionCall;
import main.lang.util.types.Number;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 08.10.2020
 */
public class Add extends Function {

    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "+";
            }

            @Override
            public Value evaluate(Scope scope) {
                Number ans = new Number(0.d);
                for (var expr: args) {
                    Value res = expr.evaluate(scope);
                    if (! (res instanceof Number))
                        throw new IllegalArgumentException("Cannot cast :" + expr + " to Number");
                    ans.add(((Number) res).getInnerValue());
                }
                return ans;
            }
        };
    }


}
