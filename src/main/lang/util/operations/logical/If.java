package main.lang.util.operations.logical;

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
public class If extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "if";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() <= 1 || args.size() > 3)
                    throw new IllegalArgumentException("Wrong arg count : if");
                Bool statement = (Bool) args.get(0).evaluate(scope);

                if (statement.getInnerValue())
                    return args.get(1).evaluate(scope);
                else {
                    if (args.size() == 3) {
                        return args.get(2).evaluate(scope);
                    }
                }
                return null;
            }
        };
    }
}
