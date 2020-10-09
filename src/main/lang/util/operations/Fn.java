package main.lang.util.operations;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.*;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 09.10.2020
 */
public class Fn extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            public Value evaluate(Scope scope) {
                if (args.size() != 2)
                    throw new IllegalArgumentException("fn takes 2 args");
                List l = (List) args.get(0);
                ArrayList<Variable> vars = new ArrayList<>();
                for (var expr:l.getInnerValue()) {
                    Variable v = (Variable)expr;
                    vars.add(v);
                }
                return new CustomFunction(vars, args.get(1));
            }

            @Override
            protected String getName() {
                return "fn";
            }
        };
    }
}
