package main.lang.types.functions;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.Variable;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 09.10.2020
 */
public class CustomFunction extends Function {
    ArrayList<Variable> variables;
    private String name;
    private Expression funcBody;
    public CustomFunction(ArrayList<Variable> variables, Expression body) {
        this.variables = variables;
        this.funcBody = body;
    }
    public void setName(String s) {
        name = s;
    }

    @Override
    public String toString() {
        if (name == null)
            return "{Function : privateFunc}";
        return "{Function :" + name + "}";
    }

    @Override
    public FunctionCall getFunctionCall(ArrayList<Expression> args) {
        return new FunctionCall(args) {
            @Override
            protected String getName() {
                if (name == null)
                    return "pf";
                return  name;
            }

            @Override
            public Value evaluate(Scope scope) {
                Scope innerScope = new Scope(scope);
                if (variables.size() != args.size())
                    throw new IllegalArgumentException("Wrong arg count: privateFunc");
                for (int i = 0; i < args.size(); i++) {
                    innerScope.setVariableValue(variables.get(i).getName() ,args.get(i).evaluate(scope));
                }
                return funcBody.evaluate(innerScope);
            }
        };
    }
}
