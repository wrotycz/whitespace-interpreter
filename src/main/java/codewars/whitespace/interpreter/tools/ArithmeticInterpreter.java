package codewars.whitespace.interpreter.tools;

import codewars.whitespace.interpreter.model.ArithmeticOperation;
import codewars.whitespace.interpreter.model.InterpreterState;

import java.io.InputStream;

public class ArithmeticInterpreter implements Interpreter {

    @Override
    public void interpret(String code, InterpreterState state, InputStream input) {
        ArithmeticOperation operation = parseOperation(code, state);
        switch (operation) {
            case ADD:
                Integer a1 = state.stackPop();
                Integer b1 = state.stackPop();
                state.stackPush(b1 + a1);
                break;
            case SUBTRACT:
                Integer a2 = state.stackPop();
                Integer b2 = state.stackPop();
                state.stackPush(b2 - a2);
                break;
            case MULTIPLY:
                Integer a3 = state.stackPop();
                Integer b3 = state.stackPop();
                state.stackPush(b3 * a3);
                break;
            case DIVIDE:
                Integer a4 = state.stackPop();
                if (a4.equals(0)) {
                    throw new IllegalStateException("Division by zero");
                }
                Integer b4 = state.stackPop();
                state.stackPush(Math.floorDiv(b4, a4));
                break;
            case MOD:
                Integer a5 = state.stackPop();
                Integer b5 = state.stackPop();
                state.stackPush(b5 % a5);
                break;
            default:
                throw new IllegalStateException(operation.toString());
        }
    }

    private ArithmeticOperation parseOperation(String code, InterpreterState state) {
        return ArithmeticOperation.byCode(code.substring(state.getCursor(), state.incrementCursor(2)))
                .orElseThrow(() -> new IllegalStateException("Cannot parse expression"));
    }
}
