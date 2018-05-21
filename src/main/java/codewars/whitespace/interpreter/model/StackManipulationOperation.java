package codewars.whitespace.interpreter.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum StackManipulationOperation {

    PUSH("s"),
    DUPLICATE_NTH("ts"),
    DISCARD_BELOW_TOP("tn"),
    DUPLICATE_TOP("ns"),
    SWAP_TOP("nt"),
    DISCARD_TOP("nn");

    private final String code;

    private static final Map<String, StackManipulationOperation> mMap = Collections.unmodifiableMap(initializeMapping());

    StackManipulationOperation(String code) {
        this.code = code;
    }

    public static Optional<StackManipulationOperation> byCode(String code) {
        return Optional.ofNullable(mMap.get(code));
    }

    private static Map<String, StackManipulationOperation> initializeMapping() {
        Map<String, StackManipulationOperation> mMap = new HashMap<>();
        for (StackManipulationOperation op : StackManipulationOperation.values()) {
            mMap.put(op.code, op);
        }
        return mMap;
    }

    public String getCode() {
        return code;
    }
}
