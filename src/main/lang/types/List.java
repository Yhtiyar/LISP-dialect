package main.lang.types;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import java.util.ArrayList;

/**
 * @author Yhtyyar created on 07.10.2020
 */
public class List extends Value {
    private ArrayList<Expression> innerValue;

    public List (ArrayList<Expression> values) {
        this.innerValue = values;
    }

    public ArrayList<Expression> getInnerValue() {
        return this.innerValue;
    }

    @Override
    public Value evaluate(Scope scope) {
        for (int i = 0; i < innerValue.size(); ++i) {
            if (!(innerValue.get(i) instanceof Value) || (innerValue.get(i) instanceof List))
                innerValue.set(i, innerValue.get(i).evaluate(scope));
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (var e:innerValue) {
            sb.append(e).append(' ');
        }
        return sb.append(']').toString();
    }
}
