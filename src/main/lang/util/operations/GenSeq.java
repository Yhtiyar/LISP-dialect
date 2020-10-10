package main.lang.util.operations;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.List;
import main.lang.types.Number;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 10.10.2020
 */
public class GenSeq extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "gen-seq";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() != 3) {
                    throw new IllegalArgumentException("wrong arg count: gen-seq");
                }
                Number first = (Number) args.get(0).evaluate(scope);
                Number count = (Number) args.get(1).evaluate(scope);
                Function f = (Function) args.get(2).evaluate(scope);

                ArrayList<Expression> ans = new ArrayList<>();
                Double curr = first.getInnerValue();
                for (int i = 1; i <= count.getInnerValue(); i++) {
                    ans.add(new Number(curr));

                    ArrayList<Expression> temp = new ArrayList<>();
                    temp.add(new Number(curr));

                    curr = ((Number)f.getFunctionCall(temp).evaluate(scope)).getInnerValue();
                }
                return new List(ans);
            }
        };
    }
}
