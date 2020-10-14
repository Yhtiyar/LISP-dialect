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
public class Or extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "or";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() == 0)
                    throw new IllegalArgumentException("Wrong arg count : and");
                for (var a : args) {
                    Boolean t = ((Bool)a.evaluate(scope)).getInnerValue();
                    if (t)
                        return new Bool(true);

                }
                return new Bool(false);
            }
        };
    }
}
