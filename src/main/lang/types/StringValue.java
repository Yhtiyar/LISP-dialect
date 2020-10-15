package main.lang.types;

import main.lang.Value;

/**
 * @author Yhtyyar created on 10.10.2020
 */
public class StringValue extends Value {
    private String val;
    public StringValue(String val) {
        this.val = val;
    }
    public String getInnerValue() {
        return val;
    }
    @Override
    public String toString() {
        return "\"" + val + "\"";
    }
}
