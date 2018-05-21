package codewars.whitespace.interpreter.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum FlowControlOperation {

    MARK("ss"),
    CALL("st"),
    JUMP("sn"),
    EQ_ZERO("ts"),
    LT_ZERO("tt"),
    EXIT_SUBROUTINE("tn"),
    EXIT("nn");

    private final String code;

    private static final Map<String, FlowControlOperation> mMap = Collections.unmodifiableMap(initializeMapping());

    FlowControlOperation(String code) {
        this.code = code;
    }

    public static Optional<FlowControlOperation> byCode(String code) {
        return Optional.ofNullable(mMap.get(code));
    }

    private static Map<String, FlowControlOperation> initializeMapping() {
        Map<String, FlowControlOperation> mMap = new HashMap<>();
        for (FlowControlOperation op : FlowControlOperation.values()) {
            mMap.put(op.code, op);
        }
        return mMap;
    }

    public String getCode() {
        return code;
    }
}
