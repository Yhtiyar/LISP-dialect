package main.lang.util.operations;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 10.10.2020
 */
public class Do extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "do";
            }

            @Override
            public Value evaluate(Scope scope) {
                Value last = null;
                for (var a:args) {
                    last = a.evaluate(scope);
                }
                return last;
            }
        };
    }
}
