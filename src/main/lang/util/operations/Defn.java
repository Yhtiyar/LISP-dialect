package main.lang.util.operations;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.List;
import main.lang.types.Variable;
import main.lang.types.functions.CustomFunction;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 14.10.2020
 */
public class Defn extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "defn";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() != 3) {
                    throw new IllegalArgumentException("defn Expected 3 args, found " + args.size());
                }
                if (!(args.get(0) instanceof Variable)) {
                    throw new IllegalArgumentException("def first value should be variable");
                }

                List l = (List) args.get(1);
                boolean isInf =  false;
                ArrayList<Variable> vars = new ArrayList<>();
                for (int i = 0; i < l.getInnerValue().size(); i++) {
                    Variable v = (Variable)l.getInnerValue().get(i);
                    if (v.isInfinity()) {
                        if (i != l.getInnerValue().size() - 1)
                            throw new IllegalArgumentException("Infinity var should bew declared at the end");
                        v = new Variable(v.getName().substring(1));
                        vars.add(v);
                        isInf = true;
                        break;
                    }
                    vars.add(v);
                }

                CustomFunction cf=  new CustomFunction(vars, args.get(2));
                cf.setName(((Variable) args.get(0)).getName());
                if (isInf)
                    cf.setInfinity();
                scope.setVariableValue(((Variable) args.get(0)).getName(), cf);
                return cf;
            }
        };
    }
}
