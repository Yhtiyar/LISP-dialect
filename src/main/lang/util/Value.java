package main.lang.util;

import main.lang.Expression;
import main.lang.Scope;

/**
 * @author Yhtyyar created on 07.10.2020
 */
public abstract class Value implements Expression {

    @Override
    public Value evaluate(Scope scope) {
        return this;
    }
}
