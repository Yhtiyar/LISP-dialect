package main.lang.parser;

/**
 * @author Yhtyyar created on 10.02.2020
 **/
public  class BaseParser {
    private final ExpressionSource source;
    protected char ch;

    public BaseParser(final ExpressionSource source) {
        this.source = source;
    }

    protected void nextChar() {
        ch = (source.hasNext()) ? source.next() : '\0';
    }
    protected int getPosition(){
        return this.source.getPosition();
    }
    protected boolean test(char testValue) {
        if (ch == testValue) {
            nextChar();
            return true;
        }
        return false;
    }

    protected boolean between(char left, char right) {
        return left <= ch && ch <= right;
    }

    protected void skipWhitespace() {
        while(Character.isWhitespace(ch)) {
            test(ch);
        }
    }
}