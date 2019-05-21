package com.intern.rpn;

import java.util.Map;

public class RPNFunc extends BaseRPNElem {

    public Type checkType () {
        return Type.FUN;
    }

    String id;
    Map<String, Integer> varList;

    public void Evaluate() {}

}
