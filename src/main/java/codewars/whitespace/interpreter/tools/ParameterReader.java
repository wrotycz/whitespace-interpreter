package codewars.whitespace.interpreter.tools;

import codewars.whitespace.interpreter.model.InterpreterState;

public class ParameterReader {

    private static final String END = "END";

    public static Integer readNumber(String code, InterpreterState state) {
        String prefix = parseNumberSign(code, state);
        StringBuilder bitBuffer = new StringBuilder();

        for (String read = readBit(code, state); !END.equals(read); read = readBit(code, state)) {
            bitBuffer.append(read);
        }

        String bits = bitBuffer.toString();
        String bitsNormalized = bits.isEmpty() ? "0" : bits;

        return Integer.parseInt(prefix + Integer.parseInt(bitsNormalized, 2));
    }

    public static Integer readLabel(String code, InterpreterState state) {
        StringBuilder buffer = new StringBuilder();

        for (String read = readBit(code, state); !END.equals(read); read = readBit(code, state)) {
            buffer.append(read);
        }

        return Integer.parseInt(buffer.toString(), 2);
    }

    private static String readBit(String code, InterpreterState state) {
        int cursor = state.getCursor();
        String codedBit = code.substring(cursor, cursor + 1);
        state.incrementCursor(1);
        switch (codedBit) {
            case "s":
                return "0";
            case "t":
                return "1";
            case "n":
                return END;
            default:
                throw new IllegalStateException("Cannot parse, cursor: " + state.getCursor());
        }
    }

    private static String parseNumberSign(String code, InterpreterState state) {
        int cursor = state.getCursor();
        String codedSign = code.substring(cursor, cursor + 1);
        state.incrementCursor(1);
        switch (codedSign) {
            case "t":
                return "-";
            case "s":
                return "";
            default:
                throw new IllegalStateException("Cannot parse, cursor: " + state.getCursor());
        }
    }

}
