package main.lang.util.operations;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.*;
import main.lang.types.functions.CustomFunction;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;

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
                CustomFunction cf = new CustomFunction(vars, args.get(1));;
                if (isInf)
                    cf.setInfinity();
                return cf;
            }

            @Override
            protected String getName() {
                return "fn";
            }
        };
    }
}
