package com.intern.rpn;

import java.util.Map;

public class RPNVariable extends BaseRPNElem {

    public Type checkType () {
        return Type.VAR;
    }

    private String id;
    private Map<String, Integer> varList;

    RPNVariable(String name, Map<String, Integer> vars) {
        this.id = name;
        this.varList = vars;
    }

    public void Evaluate() {
        if (!varList.containsKey(id))
            throw new RuntimeException("INVALID ID");
        pushArg(varList.get(id));
    }

}
