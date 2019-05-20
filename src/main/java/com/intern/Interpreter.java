package com.intern;

import java.util.List;

class Interpreter {

    private List<BaseRPNElem> rpnList;

    Interpreter(List<BaseRPNElem> rpnList) {
        this.rpnList = rpnList;
    }

    int evaluateRPN() {
        for (BaseRPNElem it: rpnList) {
            it.Evaluate();
        }
        return BaseRPNElem.getResult();
    }

}
