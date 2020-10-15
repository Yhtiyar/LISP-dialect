package main;

import main.lang.Expression;
import main.lang.Scope;
import main.lang.util.StdLibrary;
import main.lang.parser.Parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scope globalScope = StdLibrary.getGlobalScope();

        if (args.length > 0) {
            String fileName = args[0];
            System.out.println("--------Loading File : " + fileName + " ----------");
            StringBuilder source = new StringBuilder();
            try {
                Scanner fc = new Scanner(new FileInputStream(fileName));

                while (fc.hasNext()) {
                    source.append(fc.nextLine());
                }
            }
            catch (FileNotFoundException e) {
                System.err.println("File not found " + fileName);
            }
            //System.out.println();
            System.out.println("-----File Successfully loaded---------");
            FileRunner.run(source.toString(), globalScope);
            FileRunner.run("(defn != [a b] (not (= a b)))", globalScope);
        }


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
