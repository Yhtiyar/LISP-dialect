package main.lang.parser;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.types.StringValue;
import main.lang.types.functions.Function;
import main.lang.types.List;
import main.lang.types.Number;
import main.lang.types.Variable;
import main.lang.types.functions.PrivateFunctionCall;

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
        private String parseStringValue () {
            StringBuilder sb = new StringBuilder();
            while (ch != '\"' && ch != '\0') {
                sb.append(ch);
                nextChar();
            }
            nextChar();
            skipWhitespace();
            return sb.toString();
        }
        private String parseUnaryString() {
            skipWhitespace();
            StringBuilder sb = new StringBuilder();
            while (ch != ')'  && ch != ' ' && ch != '\0' && ch!=']') {
                if (ch == '&')
                    throw new IllegalArgumentException("Wrong use of: &");
                sb.append(ch);
                nextChar();
            }
            if (sb.toString().length() == 0)
                throw new IllegalArgumentException("Cannot parse expression, stuck at" + ch);
            skipWhitespace();

            return sb.toString();
        }
        private Expression parseList() {
            skipWhitespace();
            List l = new List(new ArrayList<>());
            while (ch!=']' && ch!='\0') {
                l.getInnerValue().add(parseExpression());
            }
            if (ch == '\0')
                throw new IllegalArgumentException("Unexpected ending");
            nextChar();
            skipWhitespace();
            return  l;
        }
        private Expression parseUnary() {
            skipWhitespace();
            if (ch == '\0') {
                throw new IllegalArgumentException("Unexpected end of expr");
            }
            if (test('[')) {
                return parseList();
            }
            if (test('\"')) {

                return new StringValue(parseStringValue());
            }
            if (test('&')) {
                Variable v = (Variable) parseUnary();
                if (v.getName().charAt(0) == '&')
                    throw new IllegalArgumentException("Wrong var name " + v.getName());

                return new Variable("&"+v.getName());
            }
            if (test('~')) {
                Variable v = (Variable) parseUnary();
                if (v.getName().charAt(0) == '~')
                    throw new IllegalArgumentException("Wrong var name " + v.getName());

                return new Variable("~"+v.getName());
            }
            if (between('0', '9') || ch == '-') {
                String numb = parseUnaryString();
                try {
                   double val = Double.parseDouble(numb);
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
                try {
                    f = globalScope.getFunction(funcName);
                }
                catch (Exception e) {
                    ArrayList<Expression> args = new ArrayList<>();
                    while (ch != ')') {
                        args.add(parseExpression());
                        if (ch == '\0')
                            throw new IllegalArgumentException("Missing )");
                    }
                    nextChar();
                    skipWhitespace();
                    PrivateFunctionCall pc = new PrivateFunctionCall(args, funcName);
                    return pc;
                }
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
            if (ch == '\0') {
                throw new IllegalArgumentException("Unexpected end of expr");
            }

            return parseUnary();
        }
    }
}
