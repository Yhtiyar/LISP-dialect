package main.lang.util.operations;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.List;
import main.lang.types.Number;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 10.10.2020
 */
public class Range extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "range";
            }
            @Override
            public Value evaluate(Scope scope) {
                //TODO add range step
                if (args.size() != 2 )
                    throw new IllegalArgumentException("Incorrecr arg count: range");
                Number n1 = (Number) args.get(0).evaluate(scope);
                Number n2 = (Number) args.get(1).evaluate(scope);
                ArrayList<Expression> ans = new ArrayList<>();
                if (n1.getInnerValue() > n2.getInnerValue()) {
                    for (Double d1 = n1.getInnerValue(); d1 >= n2.getInnerValue(); d1-=1) {
                        ans.add(new Number(d1));
                    }
                    return new List(ans);
                }
                for (Double d1 = n1.getInnerValue(); d1 <= n2.getInnerValue(); d1+=1) {
                    ans.add(new Number(d1));
                }
                return new List(ans);
            }
        };
    }
}
