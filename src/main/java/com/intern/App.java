package com.intern;

import java.io.IOException;


public class App 
{
    public static void main( String[] args ) {
        Tokenizer tokenizer = new Tokenizer();
        try {
            tokenizer.parseInput();
//            System.out.println(tokenizer.toString());

            SyntaxParser parser = new SyntaxParser();

            Interpreter interpreter = parser.parseTokens(tokenizer.getTokenList());

            System.out.println(interpreter.evaluateRPN());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (SyntaxException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("RUNTIME ERROR");
        }

    }
}
