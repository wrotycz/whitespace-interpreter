package codewars.whitespace.interpreter.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * InstructionModificationParameter
 */
public enum IMP {

    STACK_MANIPULATION("s"),
    ARITHMETIC("ts"),
    HEAP_ACCESS("tt"),
    INPUT_OUTPUT("tn"),
    FLOW_CONTROL("n");

    private final String code;

    private static final Map<String, IMP> mMap = Collections.unmodifiableMap(initializeMapping());

    IMP(String code) {
        this.code = code;
    }

    public static Optional<IMP> byCode(String code) {
        return Optional.ofNullable(mMap.get(code));
    }

    private static Map<String, IMP> initializeMapping() {
        Map<String, IMP> mMap = new HashMap<>();
        for (IMP imp : IMP.values()) {
            mMap.put(imp.code, imp);
        }
        return mMap;
    }

    public String getCode() {
        return code;
    }
}
