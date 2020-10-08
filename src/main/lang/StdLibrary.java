package main.lang;

import main.lang.operations.Add;
import main.lang.operations.Multiply;
import main.lang.operations.Subtract;
import main.lang.util.types.FunctionPointer;

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

        scope.setFunction(new FunctionPointer("+", new Add()));
        scope.setFunction(new FunctionPointer("-", new Subtract()));
        scope.setFunction(new FunctionPointer("*", new Multiply()));
    }

}
