package main.lang.types;

import main.lang.Value;

/**
 * @author Yhtyyar created on 07.10.2020
 */
public class Number extends Value {
    double innerValue;

    public Number(double value) {
        this.innerValue = value;
    }

    public double getInnerValue() {
        return innerValue;
    }

    public void add(double d) { this.innerValue += d; }
    public void multiply(double d) { this.innerValue *= d; }
    public void divide(double d) { this.innerValue /= d; }
    public boolean isInt() {
        double d = innerValue;
        int intPart = (int)d;
        if (d - intPart == 0)
            return true;
        return false;
    }
    public int intValue() {
        return (int)innerValue;
    }
    @Override
    public String toString() {
        double d = innerValue;
        int intPart = (int)d;
        if (d - intPart == 0)
            return String.valueOf(intPart);
        return String.valueOf(d);
    }
}
