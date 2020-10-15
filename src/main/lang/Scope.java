package main.lang;


import main.lang.types.List;
import main.lang.types.functions.Function;
import main.lang.types.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yhtyyar created on 08.10.2020
 */
public class Scope {

    private Map<String, Variable> variablesMap;

    private Scope fatherScope;

    public Scope () {
        this(null);
    }
    public Scope (Scope fatherScope) {
        this.fatherScope = fatherScope;
        this.variablesMap = new HashMap<>();
    }

    public Variable getVariable(String name) {
        Variable answer = variablesMap.get(name);
        if (answer == null) {
            if (fatherScope != null)
                answer = fatherScope.getVariable(name);
            if (answer == null) {
                throw new IllegalArgumentException("Variable " + name + " not found");
            }
        }
        return answer;
    }


    public void setVariableValue(String name, Value val) {
        if (val.isList()) {
            //TODO rewrite parser, and send copy when getInnervalue
            val = new List((ArrayList<Expression>) ((List)val).getInnerValue().clone());
        }
        variablesMap.put(name, new Variable(name, val));
    }


    public Function getFunction(String name) {
        Variable ans = getVariable(name);
        if (ans.getValue().isFunction())
            return (Function) ans.getValue();
        throw new IllegalArgumentException(name + " is not a Function");
    }

}
