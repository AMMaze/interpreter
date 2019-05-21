package com.intern.rpn;

public class RPNJump extends BaseRPNElem {

    public Type checkType () {
        return Type.JMP;
    }

    public void setJump (int idx) {
        jumpTarget = idx;
    }

    public void Evaluate() {
    }

    public RPNJump() {}

    public RPNJump(int idx) {
        jumpTarget = idx;
    }

}
