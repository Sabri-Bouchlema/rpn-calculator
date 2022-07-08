package kata.rpncalculator.services;

import kata.rpncalculator.enums.Operand;
import kata.rpncalculator.store.Operands;
import kata.rpncalculator.store.Stacks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorServiceImpl.class);
    public HashMap<Integer, ArrayDeque<Double>> getStacks() {
        return Stacks.getInstance();
    }

    @Override
    public Map.Entry<Integer, ArrayDeque<Double>> createStack() {
        int id = Instant.now().getNano();
        Stacks.getInstance().put(id, new ArrayDeque());
        return Map.entry(id, Stacks.getInstance().get(id));
    }

    @Override
    public void pushToStackById(final Integer id, final Double value) {
        getStackById(id).add(value);
    }

    public ArrayDeque<Double> getStackById(final Integer id) {
        Assert.notNull(id, "Id must not be null");
        Optional<ArrayDeque<Double>> optionalStack = Optional.ofNullable(Stacks.getInstance().get(id));
        if(optionalStack.isPresent()) {
            return optionalStack.get();
        } else {
            logger.error("Stack not found with id {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stack not found");
        }
    }

    @Override
    public void deleteStackById(final Integer id) {
        Stacks.getInstance().remove(id);
    }

    @Override
    public void applyOperand(final Operand op, final Integer id){
        Assert.notNull(op, "Operand must not be null");
        ArrayDeque<Double> stack = getStackById(id);
        Assert.isTrue(stack.size() >= 2, "Could not apply operand, stack must contain at least 2 elements");
        Double latest1 = stack.removeLast();
        Double latest2 = stack.removeLast();
        OperandExecutor executor = Operands.getInstance().get(op);
        Assert.notNull(executor, "Could not find and executor for the following operand" + op.name());
        stack.add(executor.execute(latest1,latest2));
    }

    @Override
    public Operand[] getOperands() {
        return Operand.values();
    }
}
