package com.intern;

class Token {

    public enum Type {
        NUM,
        BOP,
        UOP,
        BRACE
    }

    private String value;
    private Type type;

    String getValue() {
        return value;
    }
    Type getType() {
        return type;
    }

    Token(String v, Type t) {
        this.value = v;
        this.type = t;
    }


}
