package kata.rpncalculator.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.rpncalculator.enums.Operand;
import kata.rpncalculator.services.CalculatorService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rpn")
@Api(tags = "rpn")
@Tag(name = "rpn", description = "RPN API")
public class StackController {

    private CalculatorService calculatorService;

    public StackController(final CalculatorService calculatorService){
        this.calculatorService = calculatorService;
    }

    @ApiOperation(value = "List all the operand")
    @GetMapping("/op")
    public Operand[] getAllOperands(){
        return this.calculatorService.getOperands();
    }

    @ApiOperation(value = "Apply an operand to a stack")
    @PostMapping("/op/{op}/stack/{stack_id}")
    public void applyOperand(@PathVariable("op") Operand op, @PathVariable("stack_id") Integer stackId){
        this.calculatorService.applyOperand(op, stackId);
    }

    @ApiOperation(value = "List the available stacks")
    @GetMapping("/stack")
    public HashMap<Integer, ArrayDeque<Double>> getAllStacks(){
        return this.calculatorService.getStacks();
    }

    @ApiOperation(value = "Create a new stack")
    @PostMapping("/stack")
    public Map.Entry<Integer, ArrayDeque<Double>> createStack(){
         return this.calculatorService.createStack();
    }

    @ApiOperation(value = "Delete a stack")
    @DeleteMapping("/stack/{stack_id}")
    public void deleteStackById(@PathVariable("stack_id") Integer stackId){
        this.calculatorService.deleteStackById(stackId);
    }

    @ApiOperation(value = "Push a new value to a stack")
    @PostMapping("/stack/{stack_id}")
    public void pushToStackById(@PathVariable("stack_id") Integer stackId, @RequestBody Double value){
        this.calculatorService.pushToStackById(stackId, value);
    }

    @ApiOperation(value = "Get a stack")
    @GetMapping("/stack/{stack_id}")
    public ArrayDeque<Double> getStackById(@PathVariable("stack_id") Integer stackId){
        return this.calculatorService.getStackById(stackId);
    }
}
