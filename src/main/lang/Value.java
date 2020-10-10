package main.lang;

import main.lang.types.StringValue;
import main.lang.types.functions.Function;
import main.lang.types.List;
import main.lang.types.Number;

/**
 * @author Yhtyyar created on 07.10.2020
 */
public abstract class Value implements Expression {

    @Override
    public Value evaluate(Scope scope) {
        return this;
    }

    public boolean isNumber() {
        return (this instanceof  Number);
    }
    public boolean isFunction() {
        return  (this instanceof Function);
    }
    public boolean isList() {
        return (this instanceof List);
    }
    public boolean isStringValue() {
        return (this instanceof StringValue);
    }
}
