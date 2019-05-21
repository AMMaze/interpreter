package com.intern.rpn;

import java.util.EmptyStackException;

public class RPNJumpBack extends BaseRPNElem {
    public Type checkType () {
        return Type.JMP;
    }

    public void Evaluate() {
        int jmp, op;
        try {
            op = popArg();
            jmp = popArg();
        } catch (EmptyStackException e) {
            throw new RuntimeException("Incorrect number of operands");
        }
        jumpTarget = jmp;
        pushArg(op);
    }
}
