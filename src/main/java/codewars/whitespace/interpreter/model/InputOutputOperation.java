package codewars.whitespace.interpreter.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum InputOutputOperation {

    OUTPUT_CHARACTER("ss"),
    OUTPUT_NUMBER("st"),
    STORE_INPUT_CHARACTER("ts"),
    STORE_INPUT_NUMBER("tt");

    private final String code;

    private static final Map<String, InputOutputOperation> mMap = Collections.unmodifiableMap(initializeMapping());

    InputOutputOperation(String code) {
        this.code = code;
    }

    public static Optional<InputOutputOperation> byCode(String code) {
        return Optional.ofNullable(mMap.get(code));
    }

    private static Map<String, InputOutputOperation> initializeMapping() {
        Map<String, InputOutputOperation> mMap = new HashMap<>();
        for (InputOutputOperation op : InputOutputOperation.values()) {
            mMap.put(op.code, op);
        }
        return mMap;
    }

    public String getCode() {
        return code;
    }
}
