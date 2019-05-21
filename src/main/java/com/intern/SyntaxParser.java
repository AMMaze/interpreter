package com.intern;


import com.intern.rpn.*;

import java.util.*;

class SyntaxParser {

    private List<BaseRPNElem> rpnElemList;
    private ListIterator<Token> tokenIterator;
    private Map<String, RPNFunc> functions;
    private Token lastToken;
    private int startProg;
    private RPNFunc currentFunc;
    private int argNum;

    SyntaxParser() {
        rpnElemList = new LinkedList<>();
        tokenIterator = null;
        lastToken = null;
        startProg = 0;
        currentFunc = null;
        functions = new HashMap<>();
        argNum = 0;
    }

    Interpreter parseTokens(List<Token> tokenList) throws SyntaxException {
        tokenIterator = tokenList.listIterator();
        try {
            funcList();
            startProg = rpnElemList.size();
            expL();
        } catch (NoSuchElementException e) {
            throw new SyntaxException();
        }
        if (tokenIterator.hasNext())
            throw new SyntaxException();
        return new Interpreter(rpnElemList, startProg);
    }

    private void funcList() throws SyntaxException {
        if (lastToken == null) {
            lastToken = tokenIterator.next();
        }

        if (lastToken.getType().equals(Token.Type.ID)) {

            if (functions.containsKey(lastToken.getValue()))
                return;
            currentFunc = new RPNFunc(lastToken.getValue(), rpnElemList.size());
            functions.put(currentFunc.getId(), currentFunc);
            rpnElemList.add(currentFunc);

            lastToken = null;
            if(!tokenIterator.next().getValue().equals("(")) {
                throw new SyntaxException();
            }

            params();

            if (lastToken == null) {
                lastToken = tokenIterator.next();
            }
            if (!lastToken.getValue().equals(")")
                    || !tokenIterator.next().getValue().equals("=")
                    || !tokenIterator.next().getValue().equals("{")) {
                throw new SyntaxException();
            }
            lastToken = null;

            expL();

            if (lastToken == null) {
                lastToken = tokenIterator.next();
            }
            if (!lastToken.getValue().equals("}")) {
                throw new SyntaxException();
            }
            lastToken = null;

            rpnElemList.add(new RPNJumpBack(rpnElemList.size()));
            currentFunc = null;

            funcList();
        }
    }

    private void params() throws SyntaxException {
        if (lastToken == null) {
            lastToken = tokenIterator.next();
        }
        if (!lastToken.getType().equals(Token.Type.ID)) {
            throw new SyntaxException();
        }

        currentFunc.putParam(lastToken.getValue());


        lastToken = tokenIterator.next();

        if (lastToken.getValue().equals(",")) {
            lastToken = null;
            params();
        }
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
                    || !tokenIterator.next().getValue().equals("{")) {
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
            if (!lastToken.getValue().equals("}")
                    || !tokenIterator.next().getValue().equals(":")
                    || !tokenIterator.next().getValue().equals("{")) {
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
            if (!lastToken.getValue().equals("}")) {
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
        } else if (lastToken.getType().equals(Token.Type.ID)) {

            //String id = lastToken.getValue();
            Token nextToken = tokenIterator.next();
            tokenIterator.previous();

            if (!nextToken.getValue().equals("(")) {
                if (!currentFunc.containsVar(lastToken.getValue()))
                    throw new RuntimeException("Parameter mismatch");
                rpnElemList.add(new RPNVariable(lastToken.getValue(), currentFunc.getVarVal()));
                lastToken = null;
            } else {
                funcArgsList();
            }

        } else {
                throw new SyntaxException();
        }
    }

    private void funcArgsList() throws SyntaxException {
//        if (lastToken == null) {
//            lastToken = tokenIterator.next();
//        }

        RPNFunc func = functions.get(lastToken.getValue());
        lastToken = null;
        if (!tokenIterator.next().getValue().equals("(")) {
            throw new SyntaxException();
        }

        RPNNumber jumpAddr = new RPNNumber(0);
        rpnElemList.add(jumpAddr);
        //rpnElemList.add(new RPNNumber(rpnElemList.size() + func.getArgc() + 2));

        int curRpnLen = rpnElemList.size();
        argNum = 1;

        funcArg();

        if (func.getArgc() != argNum) {
            throw new SyntaxException();
        }

        jumpAddr.setValue(rpnElemList.size() + 1);

        if (lastToken == null) {
            lastToken = tokenIterator.next();
        }

        if (!lastToken.getValue().equals(")")) {
            throw new SyntaxException();
        }

        lastToken = null;

        rpnElemList.add(new RPNJump(func.getAddress() - rpnElemList.size() - 1));
        rpnElemList.add(new RPNNOP());

    }

    private void funcArg() throws SyntaxException {
//        if (lastToken == null) {
//            lastToken = tokenIterator.next();
//        }
//        if (!lastToken.getType().equals(Token.Type.NUM)) {
//            throw new SyntaxException();
//        }
        expL();

//        RPNNumber num = new RPNNumber(Integer.parseInt(lastToken.getValue()));
//        lastToken = tokenIterator.next();

        if (lastToken == null) {
            lastToken = tokenIterator.next();
        }

        if (lastToken.getValue().equals(",")) {
            lastToken = null;
            argNum++;
            funcArg();
        }

//        rpnElemList.add(num);

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
