package com.intern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Tokenizer {
    static private final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
    static private final String DELIMS = "[()+\\-*/%<>=\\[\\]\\?\\{\\},]";
    static private final Set<String> SET_OF_BOP = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList(
                    "+", "-", "/", "*", "%", "<", ">", "=", ","
            ))
    );
    static private final Set<String> SET_OF_BRACKETS = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList(
                    "(", ")", "[", "]", "{", "}"
            ))
    );
    static private final Set<String> SET_OF_CONDITIONALS = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList(
                    "?", ":"
            ))
    );

    private ArrayList<String> inputStorage;

    private List<Token> tokenList;

    Tokenizer() {
        this.inputStorage = new ArrayList<>();
        this.tokenList = new LinkedList<>();
    }

    private Token.Type checkType (String c) throws SyntaxException{
        if (SET_OF_BOP.contains(c))
            return Token.Type.OP;
        if (SET_OF_BRACKETS.contains(c))
            return Token.Type.BRACKET;
        if (SET_OF_CONDITIONALS.contains(c))
            return Token.Type.COND;
        if (c.matches("[a-zA-Z_]+"))
            return Token.Type.ID;
        try {
            Integer.parseInt(c);
            return Token.Type.NUM;
        } catch (NumberFormatException e) {
            throw new SyntaxException();
        }
    }

    void parseInput () throws IOException, SyntaxException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        int lineNumber = 0;
        while ((line = reader.readLine()) != null) {
            inputStorage.add(line);
            String[] arr = line.split(String.format(WITH_DELIMITER, DELIMS));
            for (String it: arr) {
                tokenList.add(new Token(it, checkType(it), lineNumber));
            }
            lineNumber++;
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

    List<Token> getTokenList() {
        return tokenList;
    }
}
