package main.lang;

import main.lang.util.types.Function;
import main.lang.util.types.FunctionPointer;
import main.lang.util.types.Variable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yhtyyar created on 08.10.2020
 */
public class Scope {

    private Map<String, Variable> variablesMap;
    private Map<String, FunctionPointer> functionPointerMap;

    private Scope fatherScope;

    public Scope () {
        this(null);
    }
    public Scope (Scope fatherScope) {
        this.fatherScope = fatherScope;
        this.variablesMap = new HashMap<>();
        this.functionPointerMap = new HashMap<>();
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
    public void setVariable(String name, Variable var) {
        variablesMap.put(name, var);
    }

    public FunctionPointer getFunctionPointer(String name) {
        FunctionPointer answer = functionPointerMap.get(name);
        if (answer == null) {
            if (fatherScope != null)
                answer = fatherScope.getFunctionPointer(name);
            if (answer == null) {
                throw new IllegalArgumentException("Function " + name + " not found");
            }
        }
        return answer;
    }
    public void setFunction(FunctionPointer functionPointer) {
        this.functionPointerMap.put(functionPointer.getName(), functionPointer);
    }
}
