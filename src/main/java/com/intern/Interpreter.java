package com.intern;

import com.intern.rpn.BaseRPNElem;

import java.util.List;
import java.util.ListIterator;

class Interpreter {

    private List<BaseRPNElem> rpnList;
    private int startProg;

    Interpreter(List<BaseRPNElem> rpnList, int start) {
        this.rpnList = rpnList;
        this.startProg = start;
    }

    int evaluateRPN() {

        ListIterator<BaseRPNElem> iterator = rpnList.listIterator(startProg);
        int jmp = 0;
        while (iterator.hasNext()) {
            if (jmp == 0) {
                jmp = iterator.next().nextEvaluate();
            } else if (jmp > 0) {
                iterator.next();
                jmp--;
            } else {
                iterator.previous();
                jmp++;
            }
        }
        return BaseRPNElem.getResult();
    }

}
