package main.lang.util.operations.listops;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.List;
import main.lang.types.Number;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 15.10.2020
 */
public class Nth extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "nth";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() != 2)
                    throw new IllegalArgumentException("Wrong arg count: nth");
                List list = (List)args.get(0).evaluate(scope);
                Number index = (Number) args.get(1).evaluate(scope);

                //TODO change with list.get();
                return list.getInnerValue().get(index.intValue()).evaluate(scope);
            }
        };
    }
}
