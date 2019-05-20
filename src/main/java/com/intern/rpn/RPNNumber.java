package com.intern.rpn;

public class RPNNumber extends BaseRPNElem {

    private int value;

    public Type checkType() {
        return Type.NUM;
    }

    public void Evaluate() {
        pushArg(value);
    }

    public RPNNumber(int v) {
        value = v;
    }

}
