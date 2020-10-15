package main.lang.util.operations;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.List;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 15.10.2020
 */
public class DoF extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "do-f";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() != 2)
                    throw new IllegalArgumentException("Wrong arg count : do-f");
                Function f = (Function) args.get(0).evaluate(scope);
                List l = (List) args.get(1).evaluate(scope);
                for (var el : l.getInnerValue()) {
                    ArrayList<Expression> t = new ArrayList<>();
                    t.add(el.evaluate(scope));
                    f.getFunctionCall(t).evaluate(scope);
                }
                return null;
            }
        };
    }
}
