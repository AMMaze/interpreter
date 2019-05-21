package com.intern.rpn;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;

public class RPNFunc extends BaseRPNElem {

    public Type checkType () {
        return Type.FUN;
    }

    private String id;
    private Map<String, Integer> varVal;
    private ArrayList<String> varPos;
    private int address;

    public RPNFunc(String id, int address) {
        this.id = id;
        this.address = address;
        varVal = new HashMap<>();
        varPos = new ArrayList<>();
    }

    public void Evaluate() {
        int op;
        try {
//            for (String var : varPos) {
//                op = popArg();
//                varVal.put(var, op);
//            }
            for (int i = varPos.size() - 1; i >=0 ;i--) {
                op = popArg();
                varVal.put(varPos.get(i), op);
            }
        } catch (EmptyStackException e) {
            throw new RuntimeException("Incorrect number of operands");
        }
    }

    public String getId() {
        return id;
    }

    public Map<String, Integer> getVarVal() {
        return varVal;
    }

//    public Map<String, Integer> getVarPos() {
//        return varPos;
//    }

    public int getAddress() {
        return address;
    }

    public void putParam(String id) {
        if (varVal.containsKey(id))
            throw new RuntimeException("Multiple ids: " + id);
        varVal.put(id, null);
        varPos.add(id);
    }

    public boolean containsVar(String id) {
        return varVal.containsKey(id);
    }

    public int getArgc() {
        return varPos.size();
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof RPNFunc)) {
            return false;
        }

        return this.id.equals(((RPNFunc) obj).getId());
    }

}
