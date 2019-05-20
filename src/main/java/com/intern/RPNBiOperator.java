package com.intern;

import java.util.EmptyStackException;
import java.util.function.IntBinaryOperator;

public class RPNBiOperator extends RPNOperator {

    public Type checkType () {
        return super.checkType();
    }

    private IntBinaryOperator operator;

    public void Evaluate() {
        int op1, op2;
        try {
            op2 = popArg();
            op1 = popArg();
        } catch (EmptyStackException e) {
            throw new RuntimeException("Incorrect number of operands");
        }
        pushArg(operator.applyAsInt(op1, op2));
    }

    RPNBiOperator (IntBinaryOperator op) {
        operator = op;
    }

}
