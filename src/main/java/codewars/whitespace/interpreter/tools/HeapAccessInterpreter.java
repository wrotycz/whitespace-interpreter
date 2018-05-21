package codewars.whitespace.interpreter.tools;

import codewars.whitespace.interpreter.model.HeapAccessOperation;
import codewars.whitespace.interpreter.model.InterpreterState;

import java.io.InputStream;

public class HeapAccessInterpreter implements Interpreter {

    @Override
    public void interpret(String code, InterpreterState state, InputStream input) {
        HeapAccessOperation operation = parseOperation(code, state);
        switch (operation) {

            default:
                throw new IllegalStateException(operation.toString());
        }
    }

    private HeapAccessOperation parseOperation(String code, InterpreterState state) {
        return HeapAccessOperation.byCode(code.substring(state.getCursor(), state.incrementCursor(1)))
                .orElseThrow(() -> new IllegalStateException("Cannot parse expression"));
    }
}
