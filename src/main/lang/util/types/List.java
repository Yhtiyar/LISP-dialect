package main.lang.util.types;

import main.lang.util.Value;
import java.util.ArrayList;

/**
 * @author Yhtyyar created on 07.10.2020
 */
public class List extends Value {
    private ArrayList<Value> innerValue;

    public List (ArrayList<Value> values) {
        this.innerValue = values;
    }

    public ArrayList<Value> getInnerValue() {
        return this.innerValue;
    }


}
