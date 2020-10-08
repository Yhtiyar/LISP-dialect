package main.lang.util.types;

import main.lang.util.Value;

/**
 * @author Yhtyyar created on 07.10.2020
 */
public class Number extends Value {
    Double innerValue;

    public Number(Double value) {
        this.innerValue = value;
    }

    public Double getInnerValue() {
        return innerValue;
    }


    public void add(Double d) { this.innerValue += d; }
    public void multiply(Double d) { this.innerValue *= d; }
    public void divide(Double d) { this.innerValue /= d; }

    @Override
    public String toString() {
        return innerValue.toString();
    }
}
