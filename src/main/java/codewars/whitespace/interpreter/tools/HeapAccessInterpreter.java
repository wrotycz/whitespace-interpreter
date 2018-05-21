package codewars.whitespace.interpreter.tools;

import codewars.whitespace.interpreter.model.HeapAccessOperation;
import codewars.whitespace.interpreter.model.InterpreterState;

import java.io.InputStream;

public class HeapAccessInterpreter implements Interpreter {

    @Override
    public void interpret(String code, InterpreterState state, InputStream input) {
        HeapAccessOperation operation = parseOperation(code, state);
        switch (operation) {
            case STORE:
                Integer toStore = state.stackPop();
                Integer storeAddress = state.stackPop();
                state.heapPut(storeAddress, toStore);
                break;
            case GET:
                Integer address = state.stackPop();
                Integer value = state.heapGet(address);
                state.stackPush(value);
                break;
            default:
                throw new IllegalStateException(operation.toString());
        }
    }

    private HeapAccessOperation parseOperation(String code, InterpreterState state) {
        return HeapAccessOperation.byCode(code.substring(state.getCursor(), state.incrementCursor(1)))
                .orElseThrow(() -> new IllegalStateException("Cannot parse expression"));
    }
}
