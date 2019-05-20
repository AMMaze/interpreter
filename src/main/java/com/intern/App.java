package com.intern;

import java.io.IOException;

/**
 * Hello world!
 *
 */
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
    }
}
