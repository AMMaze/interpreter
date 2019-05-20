package com.intern;

import java.io.IOException;


public class App 
{
    public static void main( String[] args )
    {
        Tokenizer tokenizer = new Tokenizer();
        try {
            tokenizer.parseInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(tokenizer.toString());

        SyntaxParser parser = new SyntaxParser();

        Interpreter interpreter = parser.parseTokens(tokenizer.getTokenList());

        System.out.println("Result: " + interpreter.evaluateRPN());

    }
}
