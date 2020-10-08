package main.lang.util.types;


import main.lang.Expression;
import main.lang.Scope;
import main.lang.util.Value;

/**
 * @author Yhtyyar created on 08.10.2020
 */
public class FunctionPointer implements Expression {
    private String name;
    private Function function;
    public FunctionPointer(String name, Function function) {
        this.name = name;
        this.function = function;
    }

    public String getName() {
        return this.name;
    }

    public Function getFunction() {
        return this.function;
    }

    @Override
    public Value evaluate(Scope scope) {
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return this.name;
    }

}
