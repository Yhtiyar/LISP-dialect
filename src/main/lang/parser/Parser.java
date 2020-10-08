package main.lang.parser;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.util.types.Number;
import main.lang.util.types.Variable;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 08.10.2020
 */
public class Parser {

    public static Expression parse(String source, Scope scope) {
        return new Parse(new StringSource(source), scope).parseExpression();
    }

    private static class Parse extends BaseParser {
        private Scope globalScope;

        public Parse(ExpressionSource source, Scope globalScope) {
            super(source);
            nextChar();
            this.globalScope = globalScope;
        }

        private String parseUnaryString() {
            skipWhitespace();
            StringBuilder sb = new StringBuilder();
            while (ch != ')'  && ch != ' ' && ch!='\0') {
                sb.append(ch);
                nextChar();
            }
            return sb.toString();
        }

        private Expression parseUnary() {
            skipWhitespace();

            if (between('0', '9')) {
                String numb = parseUnaryString();
                try {
                   Double val = Double.parseDouble(numb);
                   return  new Number(val);
                }
                catch (Exception e) {
                    System.err.println(e);
                }
            }
            return new Variable(parseUnaryString());
        }

        private Expression parseFunction() {

            String funcName = parseUnaryString();

            ArrayList<Expression> args = new ArrayList<>();
            while (ch != ')') {
                args.add(parseExpression());
            }
            nextChar();

            return globalScope.getFunctionPointer(funcName).getFunction().getFunctionCall(args);
        }

        public Expression parseExpression() {
            skipWhitespace();
            if (test('(')) {
                return parseFunction();
            }
            return parseUnary();
        }
    }
}
