package main.lang;

import main.lang.util.Value;

/**
 * @author Yhtyyar created on 07.10.2020
 */
public interface Expression {
    Value evaluate(Scope scope);
}
