package main;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.StdLibrary;
import main.lang.parser.Parser;
import main.lang.util.Value;
import main.lang.util.types.List;
import main.lang.util.types.Number;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //System.out.println(Parser.parse("(+ 1 2)", StdLibrary.getGlobalScope()));
        Scope globalScope = StdLibrary.getGlobalScope();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String str = sc.nextLine();
            Expression expr = Parser.parse(str, globalScope);
            System.out.println("Parsed as : " + expr);
            System.out.println("Evaluated: " + expr.evaluate(globalScope));
        }

    }
}
