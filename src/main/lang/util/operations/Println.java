package main.lang.util.operations;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;
import main.lang.types.StringValue;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 10.10.2020
 */
public class Println extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "println";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() == 0)
                   {
                       System.out.println();
                       return new StringValue("");}
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < args.size() - 1; ++i) {
                    sb.append(args.get(i).evaluate(scope)).append("\n");
                }
                sb.append(args.get(args.size() - 1).evaluate(scope));
                System.out.println(sb.toString());
                return null;
            }
        };
    }
}
