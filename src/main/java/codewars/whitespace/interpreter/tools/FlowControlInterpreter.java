package codewars.whitespace.interpreter.tools;

import codewars.whitespace.interpreter.model.FlowControlOperation;
import codewars.whitespace.interpreter.model.InterpreterState;

import java.io.InputStream;

public class FlowControlInterpreter implements Interpreter {

    @Override
    public void interpret(String code, InterpreterState state, InputStream input) {
        FlowControlOperation operation = parseOperation(code, state);
        switch (operation) {
            case MARK:
                Integer markLabel = ParameterReader.readLabel(code, state);
                state.markLocation(markLabel);
                break;
            case CALL:
                Integer callLabel = ParameterReader.readLabel(code, state);
                state.callSubroutine(callLabel);
                break;
            case JUMP:
                Integer jumpLabel = ParameterReader.readLabel(code, state);
                state.jump(jumpLabel);
                break;
            case EQ_ZERO:
                Integer n1 = state.stackPop();
                if (n1.equals(0)) {
                    Integer eqZeroLabel = ParameterReader.readLabel(code, state);
                    state.jump(eqZeroLabel);
                }
                break;
            case LT_ZERO:
                Integer n2 = state.stackPop();
                if (n2.compareTo(0) < 0) {
                    Integer ltZeroLabel = ParameterReader.readLabel(code, state);
                    state.jump(ltZeroLabel);
                }
                break;
            case EXIT_SUBROUTINE:
                state.exitSubroutine();
                break;
            case EXIT:
                break;
            default:
                throw new IllegalStateException(operation.toString());
        }
    }

    private FlowControlOperation parseOperation(String code, InterpreterState state) {
        return FlowControlOperation.byCode(code.substring(state.getCursor(), state.incrementCursor(2)))
                .orElseThrow(() -> new IllegalStateException("Cannot parse expression"));
    }
}
