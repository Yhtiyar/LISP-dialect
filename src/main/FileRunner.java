package main;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.parser.BaseParser;
import main.lang.parser.ExpressionSource;
import main.lang.parser.Parser;
import main.lang.parser.StringSource;
import main.lang.types.List;
import main.lang.types.Number;
import main.lang.types.Variable;
import main.lang.types.functions.Function;
import main.lang.types.functions.PrivateFunctionCall;

import java.util.ArrayList;

/**
 * @author Yhtyyar created on 13.10.2020
 */
public class FileRunner {
    public static void run(String source, Scope scope) {
        Parse p = new Parse(new StringSource(source), scope);
        Expression exp = p.parseExpression();
        while (exp != null) {
            exp.evaluate(scope);
            exp = p.parseExpression();
        }

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

        public Expression parseFunction() {
            Function f;
            skipWhitespace();
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
                return null;
            }

            return parseUnary();
        }
    }
}
