package main.lang.types;

import main.lang.Value;

/**
 * @author Yhtyyar created on 13.10.2020
 */
public class Bool extends Value {
    Boolean innerValue;

    public Bool(Boolean innerValue) {
        this.innerValue = innerValue;
    }
    public Boolean getInnerValue() {
        return this.innerValue;
    }
    @Override
    public String toString() {
        return this.innerValue.toString();
    }

}
