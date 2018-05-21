package codewars.whitespace.interpreter.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum HeapAccessOperation {

    STORE("s"),
    GET("t");

    private final String code;

    private static final Map<String, HeapAccessOperation> mMap = Collections.unmodifiableMap(initializeMapping());

    HeapAccessOperation(String code) {
        this.code = code;
    }

    public static Optional<HeapAccessOperation> byCode(String code) {
        return Optional.ofNullable(mMap.get(code));
    }

    private static Map<String, HeapAccessOperation> initializeMapping() {
        Map<String, HeapAccessOperation> mMap = new HashMap<>();
        for (HeapAccessOperation op : HeapAccessOperation.values()) {
            mMap.put(op.code, op);
        }
        return mMap;
    }

    public String getCode() {
        return code;
    }
}
