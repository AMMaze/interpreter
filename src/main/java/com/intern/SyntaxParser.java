package com.intern;


import com.intern.rpn.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class SyntaxParser {

    private List<BaseRPNElem> rpnElemList;
    private ListIterator<Token> tokenIterator;
    private Token lastToken;

    SyntaxParser() {
        rpnElemList = new LinkedList<>();
        tokenIterator = null;
        lastToken = null;
    }

    Interpreter parseTokens(List<Token> tokenList) throws SyntaxException {
        tokenIterator = tokenList.listIterator();
        try {
            expL();
        } catch (NoSuchElementException e) {
            throw new SyntaxException();
        }
        if (tokenIterator.hasNext())
            throw new SyntaxException();
        return new Interpreter(rpnElemList);
    }

    private void expL() throws SyntaxException{
        if (lastToken == null) {
            lastToken = tokenIterator.next();
        }

        if (lastToken.getValue().equals("(")) {
            lastToken = null;

            expE();

            exp_L();

            if (lastToken == null) {
                lastToken = tokenIterator.next();
            }
            if (!lastToken.getValue().equals(")")) {
                throw new SyntaxException();
            }
            lastToken = null;
        } else if (lastToken.getValue().equals("[")) {
            lastToken = null;

            expL();

            if (lastToken == null) {
                lastToken = tokenIterator.next();
            }
            if (!lastToken.getValue().equals("]")
                    || !tokenIterator.next().getValue().equals("?")
                    || !tokenIterator.next().getValue().equals("(")) {
                throw new SyntaxException();
            }
            lastToken = null;

            RPNJumpZero jumpToElse = new RPNJumpZero();
            rpnElemList.add(jumpToElse);
            int jumpToElseIdx = rpnElemList.size();

            expL();

            if (lastToken == null) {
                lastToken = tokenIterator.next();
            }
            if (!lastToken.getValue().equals(")")
                    || !tokenIterator.next().getValue().equals(":")
                    || !tokenIterator.next().getValue().equals("(")) {
                throw new SyntaxException();
            }
            lastToken = null;

            RPNJump jumpAfterElse = new RPNJump();
            rpnElemList.add(jumpAfterElse);
            //rpnElemList.add(new RPNNOP());
            int jumpAfterElseIdx = rpnElemList.size();
            jumpToElse.setJump(jumpAfterElseIdx - jumpToElseIdx);

            expL();

            if (lastToken == null) {
                lastToken = tokenIterator.next();
            }
            if (!lastToken.getValue().equals(")")) {
                throw new SyntaxException();
            }
            lastToken = null;

            jumpAfterElse.setJump(rpnElemList.size() - jumpAfterElseIdx);
            rpnElemList.add(new RPNNOP());
        } else  if (lastToken.getType().equals(Token.Type.NUM)) {
            rpnElemList.add(new RPNNumber(Integer.parseInt(lastToken.getValue())));
            lastToken = null;
        } else if (lastToken.getValue().equals("-")) {
            lastToken = tokenIterator.next();
            if (!lastToken.getType().equals(Token.Type.NUM))
                throw new SyntaxException();
            rpnElemList.add(new RPNNumber(Integer.parseInt(lastToken.getValue())));
            rpnElemList.add(new RPNUnaryOperator(op -> -op));
            lastToken = null;
        }else {
            throw new SyntaxException();
        }
    }

    private void exp_L() throws SyntaxException  {
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

    private void expE () throws SyntaxException  {
        expT();
        exp_E();
    }

    private void exp_E() throws SyntaxException  {
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


    private void expT() throws SyntaxException  {
        expF();
        exp_T();
    }

    private void exp_T() throws SyntaxException {
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

    private void expF() throws SyntaxException {

//        if (lastToken == null) {
//            lastToken = tokenIterator.next();
//        }
//
//        if (lastToken.getType().equals(Token.Type.NUM)) {
//            rpnElemList.add(new RPNNumber(Integer.parseInt(lastToken.getValue())));
//            lastToken = null;
//        } else if (lastToken.getValue().equals("-")) {
//            lastToken = tokenIterator.next();
//            if (!lastToken.getType().equals(Token.Type.NUM))
//                throw new SyntaxException("SYNTAX ERROR");
//            rpnElemList.add(new RPNNumber(Integer.parseInt(lastToken.getValue())));
//            rpnElemList.add(new RPNUnaryOperator(op -> -op));
//            lastToken = null;
//        } else {
//            expL();
//        }

        expL();

    }
}
