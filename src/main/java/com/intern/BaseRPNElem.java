package com.intern;

import java.util.Stack;

public abstract class BaseRPNElem {
    public enum Type {
        NUM,
        OP
    }

    static private Stack<Integer> argsStack = new Stack<>();

    void pushArg(int arg) {
        argsStack.push(arg);
    }

    int popArg() {
        return argsStack.pop();
    }

    public abstract Type checkType();

    public abstract void Evaluate();
}
