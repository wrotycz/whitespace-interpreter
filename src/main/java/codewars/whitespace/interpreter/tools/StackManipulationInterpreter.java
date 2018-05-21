package codewars.whitespace.interpreter.tools;

import codewars.whitespace.interpreter.model.InterpreterState;
import codewars.whitespace.interpreter.model.StackManipulationOperation;

import java.io.InputStream;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.IntStream;

public class StackManipulationInterpreter implements Interpreter {

    @Override
    public void interpret(String code, InterpreterState state, InputStream input) {
        StackManipulationOperation operation = parseOperation(code, state);
        switch (operation) {
            case PUSH:
                state.stackPush(ParameterReader.readNumber(code, state));
                break;
            case DUPLICATE_NTH:
                Integer nth = ParameterReader.readNumber(code, state);

                Stack<Integer> buffer = new Stack<>();
                IntStream.range(0, nth + 1)
                        .forEach(i -> buffer.push(state.stackPop()));

                Integer valueToCopy = buffer.peek();

                IntStream.range(0, nth + 1)
                        .forEach(i -> state.stackPush(buffer.pop()));

                state.stackPush(valueToCopy);
                break;
            case DISCARD_BELOW_TOP:
                Integer n = ParameterReader.readNumber(code, state);
                if (n < 0 || n >= state.getStackSize()) {
                    state.stackRemoveAllElements();
                } else {
                    Integer top = state.stackPop();
                    IntStream.range(0, n)
                            .forEach(i -> state.stackPop());
                    state.stackPush(top);
                }
                break;
            case DUPLICATE_TOP:
                state.stackPush(state.stackPeek());
                break;
            case SWAP_TOP:
                Integer top = state.stackPop();
                Integer belowTop = state.stackPop();
                state.stackPush(top);
                state.stackPush(belowTop);
                break;
            case DISCARD_TOP:
                state.stackPop();
                break;
            default:
                throw new IllegalStateException(operation.toString());
        }
    }

    private StackManipulationOperation parseOperation(String code, InterpreterState state) {
        int cursor = state.getCursor();
        Optional<StackManipulationOperation> impOptional = StackManipulationOperation.byCode(code.substring(cursor, cursor + 1));
        if (impOptional.isPresent()) {
            state.incrementCursor(1);
            return impOptional.get();
        }
        state.incrementCursor(2);
        return StackManipulationOperation.byCode(code.substring(cursor, cursor + 2))
                .orElseThrow(() -> new IllegalStateException("Cannot parse expression"));
    }
}
