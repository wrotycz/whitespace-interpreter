package codewars.whitespace.interpreter.tools;

import codewars.whitespace.interpreter.model.InputOutputOperation;
import codewars.whitespace.interpreter.model.InterpreterState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputOutputInterpreter implements Interpreter {

    @Override
    public void interpret(String code, InterpreterState state, InputStream input) {
        InputOutputOperation operation = parseOperation(code, state);
        switch (operation) {
            case OUTPUT_CHARACTER:
                Integer charCode = state.stackPop();
                appendCharacterToResult(charCode, state);
                break;
            case OUTPUT_NUMBER:
                Integer value = state.stackPop();
                appendNumberToResult(value, state);
                break;
            case STORE_INPUT_CHARACTER:
                int readCode;
                try {
                    readCode = input.read();
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
                state.heapPut(state.stackPop(), readCode);
                break;
            case STORE_INPUT_NUMBER:
                String line;
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                    line = reader.readLine();
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
                state.heapPut(state.stackPop(), Integer.parseInt(line));
                break;
            default:
                throw new IllegalStateException(operation.toString());
        }
    }

    private InputOutputOperation parseOperation(String code, InterpreterState state) {
        int cursor = state.getCursor();
        state.incrementCursor(2);
        return InputOutputOperation.byCode(code.substring(cursor, cursor + 2))
                .orElseThrow(() -> new IllegalStateException("Cannot parse expression"));
    }

    private void appendNumberToResult(Integer number, InterpreterState state) {
        appendToResult(String.valueOf(number), state);
    }

    private void appendCharacterToResult(Integer characterCode, InterpreterState state) {
        String character = Character.toString((char) characterCode.intValue());
        appendToResult(character, state);
    }

    private void appendToResult(String text, InterpreterState state) {
        String previousResult = state.getResult().orElse("");
        state.setResult(previousResult + text);
    }
}
