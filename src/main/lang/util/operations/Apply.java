package main.lang.util.operations;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.List;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 10.10.2020
 */
public class Apply extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "apply";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() < 2)
                    throw new IllegalArgumentException("Wrong arg count: apply");

                Function f = (Function) args.get(0).evaluate(scope);
                ArrayList<Expression> templ = new ArrayList<>();
                for (int i = 1; i < args.size(); i++) {
                    Value v = args.get(i).evaluate(scope);
                    if (v.isList()) {
                        ArrayList<Expression> t = ((List)v).getInnerValue();
                        for (var e : t) {
                            templ.add(e.evaluate(scope));
                        }
                        continue;
                    }
                    templ.add(v);
                }

                return f.getFunctionCall(templ).evaluate(scope);
            }
        };
    }
}
