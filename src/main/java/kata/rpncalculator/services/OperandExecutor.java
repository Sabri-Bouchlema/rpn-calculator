package kata.rpncalculator.services;

@FunctionalInterface
public interface OperandExecutor  {
    Double execute(Double v1, Double v2);
}