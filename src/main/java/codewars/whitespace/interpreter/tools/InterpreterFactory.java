package codewars.whitespace.interpreter.tools;

import codewars.whitespace.interpreter.model.IMP;

public class InterpreterFactory {

    public static Interpreter getInterpreter(IMP imp) {

        switch (imp) {
            case STACK_MANIPULATION:
                return new StackManipulationInterpreter();
            case INPUT_OUTPUT:
                return new InputOutputInterpreter();
            case FLOW_CONTROL:
                return new FlowControlInterpreter();
            default:
                throw new IllegalStateException("Interpreter for IMP: " + imp.name() + " was not found");
        }
    }

}
