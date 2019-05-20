package com.intern.rpn;

import java.util.EmptyStackException;
import java.util.function.IntUnaryOperator;

public class RPNUnaryOperator extends RPNOperator {


    public Type checkType () {
        return super.checkType();
    }

    private IntUnaryOperator operator;

    public void Evaluate() {
        int op1;
        try {
            op1 = popArg();
        } catch (EmptyStackException e) {
            throw new RuntimeException("Incorrect number of operands");
        }
        pushArg(operator.applyAsInt(op1));
    }

    public RPNUnaryOperator (IntUnaryOperator op) {
        operator = op;
    }

}
