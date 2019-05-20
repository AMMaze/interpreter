package com.intern.rpn;

public abstract class RPNOperator extends BaseRPNElem {

    public Type checkType () {
        return Type.OP;
    }

}
