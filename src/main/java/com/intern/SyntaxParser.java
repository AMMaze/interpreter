package com.intern;


import java.util.IllegalFormatException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class SyntaxParser {

    private List<BaseRPNElem> rpnElemList;
    private ListIterator<Token> tokenIterator;
    private Token lastToken;

    SyntaxParser() {
        rpnElemList = new LinkedList<>();
        tokenIterator = null;
        lastToken = null;
    }

    Interpreter parseTokens(List<Token> tokenList) throws Exception {
        tokenIterator = tokenList.listIterator();
        expL();
        return new Interpreter(rpnElemList);
    }

    private void expL() throws Exception{
        if (lastToken == null) {
            lastToken = tokenIterator.next();
        }

        if (!lastToken.getValue().equals("(")) {
            throw new Exception("SYNTAX ERROR");
        }
        lastToken = null;

        expE();

        exp_L();

        if (lastToken == null) {
            lastToken = tokenIterator.next();
        }
        if (!lastToken.getValue().equals(")")) {
            throw new Exception("SYNTAX ERROR");
        }
        lastToken = null;
    }

    private void exp_L() throws Exception  {
        if (lastToken == null) {
            lastToken = tokenIterator.next();
        }
        if (lastToken.getValue().equals("<")) {
            lastToken = null;

            expE();
            exp_L();

            rpnElemList.add(new RPNBiOperator((int op1, int op2) -> op1 < op2 ? 1 : 0));
        } else if (lastToken.getValue().equals(">")) {
            lastToken = null;

            expE();
            exp_L();

            rpnElemList.add(new RPNBiOperator((int op1, int op2) -> op1 > op2 ? 1 : 0));
        } else if (lastToken.getValue().equals("=")) {
            lastToken = null;

            expE();
            exp_L();

            rpnElemList.add(new RPNBiOperator((int op1, int op2) -> op1 == op2 ? 1 : 0));
        }
    }

    private void expE () throws Exception  {
        expT();
        exp_E();
    }

    private void exp_E() throws Exception  {
        if (lastToken == null) {
            lastToken = tokenIterator.next();
        }
        if (lastToken.getValue().equals("+")) {
            lastToken = null;

            expT();
            exp_E();

            rpnElemList.add(new RPNBiOperator((int op1, int op2) -> op1 + op2));
        } else if (lastToken.getValue().equals("-")) {
            lastToken = null;

            expT();
            exp_E();

            rpnElemList.add(new RPNBiOperator((int op1, int op2) -> op1 - op2));
        }
    }


    private void expT() throws Exception  {
        expF();
        exp_T();
    }

    private void exp_T() throws Exception {
        if (lastToken == null) {
            lastToken = tokenIterator.next();
        }
        if (lastToken.getValue().equals("*")) {
            lastToken = null;

            expF();
            exp_T();

            rpnElemList.add(new RPNBiOperator((int op1, int op2) -> op1 * op2));
        } else if (lastToken.getValue().equals("/")) {
            lastToken = null;

            expF();
            exp_T();

            rpnElemList.add(new RPNBiOperator((int op1, int op2) -> op1 / op2));
        } else if (lastToken.getValue().equals("%")) {
            lastToken = null;

            expF();
            exp_T();

            rpnElemList.add(new RPNBiOperator((int op1, int op2) -> op1 % op2));
        }
    }

    private void expF() throws Exception {

        if (lastToken == null) {
            lastToken = tokenIterator.next();
        }

        if (lastToken.getType().equals(Token.Type.NUM)) {
            rpnElemList.add(new RPNNumber(Integer.parseInt(lastToken.getValue())));
            lastToken = null;
        } else {
            expL();
        }

    }
}
