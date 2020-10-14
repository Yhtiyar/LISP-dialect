package main.lang.util.operations.logical;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.Bool;
import main.lang.types.Number;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 13.10.2020
 */
public class Less extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "<";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() != 2)
                    throw new IllegalArgumentException("Wrong arg count, '<' ");
                Double d1 = ((Number)args.get(0).evaluate(scope)).getInnerValue();
                Double d2 = ((Number)args.get(1).evaluate(scope)).getInnerValue();

                return new Bool(d1 < d2);
            }
        };
    }
}
