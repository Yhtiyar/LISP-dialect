package main.lang.util.operations;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.List;
import main.lang.types.functions.CustomFunction;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;
import main.lang.types.Variable;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 09.10.2020
 */
public class Def extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "def";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() != 2) {
                    throw new IllegalArgumentException("def Expected 2 args, found " + args.size());
                }
                if (!(args.get(0) instanceof Variable)) {
                    throw new IllegalArgumentException("def first value should be variable");
                }
                Value val = args.get(1).evaluate(scope);
                if (val instanceof CustomFunction) {
                    ((CustomFunction) val).setName(((Variable) args.get(0)).getName());
                }
                scope.setVariableValue(((Variable) args.get(0)).getName(), val);
                return val;
            }
        };
    }
}
