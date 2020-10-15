package main.lang.util.operations.listops;

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
public class ForcePushFront extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "pushf!";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() < 2)
                    throw new IllegalArgumentException("wrong arg count: pushf!");
                List list = (List)args.get(0).evaluate(scope);

                ArrayList<Expression> temp = new ArrayList<>();
                for (int i = 1; i < args.size(); ++i) {
                    Value what = args.get(i).evaluate(scope);
                    if (what.isList()) {
                        List t = (List) what;
                        t = new List((ArrayList<Expression>) t.getInnerValue().clone());
                        temp.add(t);
                        // list.getInnerValue().add(index.getInnerValue().intValue(), new List((ArrayList<Expression>) ((List)what).getInnerValue().clone()));
                    }
                    else
                        temp.add(what);
                }
                list.getInnerValue().addAll(0, temp);
                return list;
            }
        };
    }
}
