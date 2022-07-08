package kata.rpncalculator.store;

import kata.rpncalculator.enums.Operand;
import kata.rpncalculator.services.OperandExecutor;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Operands {
    private static class OperandsHolder {
        public static final HashMap<Operand, OperandExecutor> operands = new HashMap();

        static {
            operands.put(Operand.ADD, (v1, v2) -> v1 + v2);
            operands.put(Operand.DIVIDE, (v1, v2) -> v1 / v2);
            operands.put(Operand.MULTIPLY, (v1, v2) -> v1 * v2);
            operands.put(Operand.SUBTRACT, (v1, v2) -> v1 - v2);
        }
    }

    public static HashMap<Operand, OperandExecutor> getInstance() {
        return OperandsHolder.operands;
    }
}
