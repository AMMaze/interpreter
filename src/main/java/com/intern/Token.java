package com.intern;

class Token {

    public enum Type {
        NUM,
        BOP,
        UOP,
        BRACKET,
        COND
    }

    private String value;
    private Type type;
    private int line;

    String getValue() {
        return value;
    }
    Type getType() {
        return type;
    }

    int getLine() {
        return line;
    }

    Token(String v, Type t, int l) {
        this.value = v;
        this.type = t;
        this.line = l;
    }


}
