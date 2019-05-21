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
            if (jmp == 0) {
                jmp = iterator.next().nextEvaluate();
            } else {
                iterator.next();
                jmp--;
            }
        }
        return BaseRPNElem.getResult();
    }

}
