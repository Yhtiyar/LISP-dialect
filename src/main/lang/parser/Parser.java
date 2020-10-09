package main.lang.parser;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.types.Function;
import main.lang.types.List;
import main.lang.types.Number;
import main.lang.types.Variable;

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
            while (ch != ')'  && ch != ' ' && ch != '\0' && ch!=']') {
                sb.append(ch);
                nextChar();
            }
            skipWhitespace();
            return sb.toString();
        }
        private Expression parseList() {
            skipWhitespace();
            List l = new List(new ArrayList<>());
            while (ch!=']' && ch!='\0') {
                l.getInnerValue().add(parseUnary());
            }
            nextChar();
            skipWhitespace();
            return  l;
        }
        private Expression parseUnary() {
            skipWhitespace();
            if (test('[')) {
                return parseList();
            }
            if (between('0', '9') || ch == '-') {
                String numb = parseUnaryString();
                try {
                   Double val = Double.parseDouble(numb);
                   return  new Number(val);
                }
                catch (Exception e) {
                    System.err.println(e);
                }
            }
            skipWhitespace();
            return new Variable(parseUnaryString());
        }

        private Expression parseFunction() {
            Function f;
            if (ch == '(') {
                f = (Function) parseExpression().evaluate(globalScope);
            }
            else {
                String funcName = parseUnaryString();
                f = globalScope.getFunction(funcName);
            }

            ArrayList<Expression> args = new ArrayList<>();
            while (ch != ')') {
                args.add(parseExpression());
                if (ch == '\0')
                    throw new IllegalArgumentException("Missing )");
            }
            nextChar();
            skipWhitespace();
            return f.getFunctionCall(args);
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
