package main.lang.types;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;



/**
 * @author Yhtyyar created on 08.10.2020
 */
public class Variable  implements Expression {
    private String name;
    private Value value;
    public Variable(String name) {
        this(name, null);
    }

    public Variable(String name, Value value) {
        this.name = name;
        this.value = value;

    }

    public String getName() {
        return name;
    }
    public boolean isInfinity () {
        return name.charAt(0) == '&';
    }

    public Value getValue() {
        return this.value;
    }

    @Override
    public Value evaluate(Scope scope) {
        return scope.getVariable(name).getValue();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
