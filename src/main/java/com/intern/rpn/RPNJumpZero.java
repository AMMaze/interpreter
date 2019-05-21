package com.intern.rpn;

import java.util.EmptyStackException;

public class RPNJumpZero extends BaseRPNElem {

    public BaseRPNElem.Type checkType () {
        return BaseRPNElem.Type.JMP;
    }

    private int jump;

    public void setJump (int idx) {
        jump = idx;
    }

    public void Evaluate() {
        int op1;
        try {
            op1 = popArg();
        } catch (EmptyStackException e) {
            throw new RuntimeException("Incorrect number of operands");
        }
        if (op1 == 0)
            super.jumpTarget = jump;
    }

}
