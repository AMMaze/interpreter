package com.intern;

import com.intern.rpn.BaseRPNElem;

import java.util.List;
import java.util.ListIterator;

class Interpreter {

    private List<BaseRPNElem> rpnList;

    Interpreter(List<BaseRPNElem> rpnList) {
        this.rpnList = rpnList;
    }

    int evaluateRPN() {

        ListIterator<BaseRPNElem> iterator = rpnList.listIterator();
        int jmp = 0;
        while (iterator.hasNext()) {
            jmp = jmp == 0 ? iterator.next().nextEvaluate() : jmp - 1;
        }
        return BaseRPNElem.getResult();
    }

}
