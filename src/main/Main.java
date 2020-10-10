package main;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.util.StdLibrary;
import main.lang.parser.Parser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //System.out.println(Parser.parse("(+ 1 2)", StdLibrary.getGlobalScope()));
        Scope globalScope = StdLibrary.getGlobalScope();
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String str = sc.nextLine();
                if (str.equals("")) {
                   // System.out.println("\n");
                    continue;
                }
                Expression expr = Parser.parse(str, globalScope);
               // System.out.println("Parsed as : " + expr);
                System.out.println(expr.evaluate(globalScope));
            }
            catch (Exception e) {
                System.err.println("Error : " + e);
            }
        }

    }
}
