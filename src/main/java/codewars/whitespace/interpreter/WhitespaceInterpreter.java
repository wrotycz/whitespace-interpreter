package codewars.whitespace.interpreter;

import codewars.whitespace.interpreter.model.IMP;
import codewars.whitespace.interpreter.model.InterpreterState;
import codewars.whitespace.interpreter.tools.InterpreterFactory;

import java.io.InputStream;

public class WhitespaceInterpreter {

    public static String execute(String code, InputStream input) {
        if (code == null || code.isEmpty()) {
            throw new IllegalStateException("Code empty");
        }

        return interpretCommand(prepareCode(code), new InterpreterState(0), input);
    }

    static String interpretCommand(String code, InterpreterState state, InputStream input) {
        if (state.getCursor() >= code.length()) {
            return state.getResult().orElse("");
        }
        IMP imp = parseImpCommand(code, state);

        InterpreterFactory.getInterpreter(imp)
                .interpret(code, state, input);

        return interpretCommand(code, state, input);
    }

    static IMP parseImpCommand(String code, InterpreterState state) {
        int cursor = state.getCursor();
        return IMP.byCode(code.substring(cursor, state.incrementCursor(1)))
                .orElseGet(() -> IMP.byCode(code.substring(cursor, state.incrementCursor(1)))
                        .orElseThrow(() -> new IllegalStateException("Cannot parse expression")));
    }

    static String prepareCode(String code) {
        return unbleach(removeComments(code));
    }

    /**
     * Transforms space characters to ['s','t','n'] chars.
     *
     * @param code String input code
     * @return unbleached code
     */
    static String unbleach(String code) {
        return code != null ? code.replace(' ', 's').replace('\t', 't').replace('\n', 'n') : null;
    }

    static String removeComments(String code) {
        return code != null ? code.replaceAll("[^\\t\\s\\n]", "") : null;
    }

}