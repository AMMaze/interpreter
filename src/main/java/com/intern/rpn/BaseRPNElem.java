package com.intern.rpn;

import java.util.Stack;

public abstract class BaseRPNElem {
    public enum Type {
        NUM,
        OP
    }

    static private Stack<Integer> argsStack = new Stack<>();

    static public int getResult() {
        int res = argsStack.pop();
        if (!argsStack.empty())
            throw new RuntimeException("Error: multiple results");
        return res;
    }

    void pushArg(int arg) {
        argsStack.push(arg);
    }

    int popArg() {
        return argsStack.pop();
    }

    public abstract Type checkType();

    public abstract void Evaluate();
}
