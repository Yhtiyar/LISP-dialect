package main.lang.util.operations;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.StringValue;
import main.lang.types.functions.Function;
import main.lang.types.functions.FunctionCall;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 10.10.2020
 */
public class Type extends Function {
    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                return "type";
            }

            @Override
            public Value evaluate(Scope scope) {
                if (args.size() != 1)
                    throw new IllegalArgumentException("Wrong arg count : type");
                Value v = args.get(0).evaluate(scope);
                if (v.isFunction())
                    return new StringValue("Function");
                if (v.isNumber())
                    return new StringValue("Number");
                if (v.isList())
                    return new StringValue("List");
                if (v.isStringValue())
                    return new StringValue("String");
                throw new IllegalArgumentException("Unknown type");
            }
        };
    }
}
