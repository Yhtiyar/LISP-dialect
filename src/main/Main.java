package main;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.Value;
import main.lang.types.Function;
import main.lang.types.Number;
import main.lang.util.StdLibrary;
import main.lang.parser.Parser;
import main.lang.util.operations.Fn;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //System.out.println(Parser.parse("(+ 1 2)", StdLibrary.getGlobalScope()));
        Scope globalScope = StdLibrary.getGlobalScope();
        Scanner sc = new Scanner(System.in);
        Fn f = new Fn();
        System.out.println((Function)f);
        while (true) {
            try {
                String str = sc.nextLine();
                Expression expr = Parser.parse(str, globalScope);
               // System.out.println("Parsed as : " + expr);
                System.out.println("Evaluated: " + expr.evaluate(globalScope));
            }
            catch (Exception e) {
                System.err.println("Error : " + e);
            }
        }

    }
}
