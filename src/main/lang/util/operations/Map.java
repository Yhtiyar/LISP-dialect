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
public class Map extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "map";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() != 2)
                    throw new IllegalArgumentException("Incorrect arg count : map" + args.size());
                Function f = (Function) args.get(0).evaluate(scope);

                List l = (List) args.get(1).evaluate(scope);
                ArrayList<Expression> listVals = new ArrayList<>();

                for (int i = 0; i < l.getInnerValue().size(); ++i) {
                    ArrayList<Expression> e = new ArrayList<>();
                    e.add(l.getInnerValue().get(i));
                    listVals.add(f.getFunctionCall(e).evaluate(scope));
                }
                return new List(listVals);
            }
        };
    }
}
