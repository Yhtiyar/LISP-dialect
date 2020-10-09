package main.lang.util;

import main.lang.Scope;
import main.lang.util.operations.*;


/**
 * @author Yhtyyar created on 09.10.2020
 */
public class StdLibrary {

    public static Scope getGlobalScope() {
        Scope scope = new Scope();
        loadFunctions(scope);
        return scope;
    }

    public static void loadFunctions(Scope scope) {
        scope.setVariableValue("+", new Add());
        scope.setVariableValue("-", new Subtract());
        scope.setVariableValue("*", new Multiply());
        scope.setVariableValue("def", new Def());
        scope.setVariableValue("/", new Divide());
        scope.setVariableValue("fn", new Fn());
    }

}
