package codewars.whitespace.interpreter.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum ArithmeticOperation {

    ADD("ss"),
    SUBTRACT("st"),
    MULTIPLY("sn"),
    DIVIDE("ts"),
    MOD("tt");

    private final String code;

    private static final Map<String, ArithmeticOperation> mMap = Collections.unmodifiableMap(initializeMapping());

    ArithmeticOperation(String code) {
        this.code = code;
    }

    public static Optional<ArithmeticOperation> byCode(String code) {
        return Optional.ofNullable(mMap.get(code));
    }

    private static Map<String, ArithmeticOperation> initializeMapping() {
        Map<String, ArithmeticOperation> mMap = new HashMap<>();
        for (ArithmeticOperation op : ArithmeticOperation.values()) {
            mMap.put(op.code, op);
        }
        return mMap;
    }

    public String getCode() {
        return code;
    }
}
