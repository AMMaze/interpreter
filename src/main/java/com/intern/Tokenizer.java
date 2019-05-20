package com.intern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Tokenizer {
    static private final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
    static private final String DELIMS = "[()+\\-*/%<>=]";
    static private final Set<String> SET_OF_BOP = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList(
                    "+", "-", "/", "*", "%", "<", ">", "="
            ))
    );
    static private final Set<String> SET_OF_UOP = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList(
                    "-"))
    );
    static private final Set<String> SET_OF_BRACES = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList(
                    "(", ")"
            ))
    );

    private List<Token> tokenList;

    Tokenizer() {
        this.tokenList = new LinkedList<>();
    }

    private Token.Type checkType (String c) {
        if (SET_OF_BOP.contains(c))
            return Token.Type.BOP;
        if (SET_OF_UOP.contains(c))
            return Token.Type.UOP;
        if (SET_OF_BRACES.contains(c))
            return Token.Type.BRACE;
        try {
            Integer.parseInt(c);
            return Token.Type.NUM;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Incorrect token: " + c);
        }
    }

    void parseInput () throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] arr = line.split(String.format(WITH_DELIMITER, DELIMS));
            for (String it: arr) {
                tokenList.add(new Token(it, checkType(it)));
            }

        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Token it : tokenList) {
            result.append("'").append(it.getValue()).append("', ");
        }
        result.delete(result.length() - 2, result.length());
        return result.toString();
    }

}
