package main.lang.util;

import main.lang.Scope;
import main.lang.util.math.Ln;
import main.lang.util.operations.*;
import main.lang.util.operations.arithmetics.Add;
import main.lang.util.operations.arithmetics.Divide;
import main.lang.util.operations.arithmetics.Multiply;
import main.lang.util.operations.arithmetics.Subtract;
import main.lang.util.operations.logical.*;
import main.lang.util.operations.loops.While;


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
        scope.setVariableValue("println", new Println());
        scope.setVariableValue("type", new Type());
        scope.setVariableValue("ln", new Ln());
        scope.setVariableValue("map", new Map());
        scope.setVariableValue("gen-seq", new GenSeq());
        scope.setVariableValue("range", new Range());
        scope.setVariableValue("apply", new Apply());
        scope.setVariableValue("do", new Do());

        scope.setVariableValue("while", new While());

        scope.setVariableValue(">", new Greater());
        scope.setVariableValue("<", new Less());
        scope.setVariableValue(">=", new GreaterEqual());
        scope.setVariableValue("<=", new LessEqual());
        scope.setVariableValue("=", new Equal());
        scope.setVariableValue("and", new And());
        scope.setVariableValue("or", new Or());
        scope.setVariableValue("not", new Not());
        scope.setVariableValue("if", new If());
        scope.setVariableValue("defn", new Defn());


    }

}
