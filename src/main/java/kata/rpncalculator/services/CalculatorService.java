package kata.rpncalculator.services;

import kata.rpncalculator.enums.Operand;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public interface CalculatorService {
    HashMap<Integer, ArrayDeque<Double>> getStacks();

    Map.Entry<Integer, ArrayDeque<Double>> createStack();

    void pushToStackById(Integer id, Double value);

    ArrayDeque<Double> getStackById(Integer id);

    void deleteStackById(Integer id);
    void applyOperand(Operand op, Integer id);
    Operand[] getOperands();
}
