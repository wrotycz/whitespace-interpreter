package codewars.whitespace.interpreter.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Stack;

public class InterpreterState {
    private int cursor;
    private String result;
    private int subroutineStartedCursor;

    private Stack<Integer> stack = new Stack<>();
    private Map<Integer, Integer> heap = new HashMap<>();
    private Map<String, Integer> labels = new HashMap<>();

    public InterpreterState(int cursor) {
        this.cursor = cursor;
    }

    public Integer stackPop() {
        return stack.pop();
    }

    public Integer stackPeek() {
        return stack.peek();
    }

    public Integer stackPush(Integer n) {
        return stack.push(n);
    }

    public void stackRemoveAllElements() {
        stack.removeAllElements();
    }

    public Integer heapGet(Integer address) {
        return heap.get(address);
    }

    public Integer heapPut(Integer address, Integer value) {
        return heap.put(address, value);
    }

    public void jump(String label) {
        subroutineStartedCursor = this.cursor;
        this.cursor = labels.get(label);
    }

    public void exitSubroutine() {
        this.cursor = subroutineStartedCursor;
    }

    public Integer saveLabel(String label, Integer cursor) {
        checkLabelUnique(label);
        return labels.put(label, cursor);
    }

    public void incrementCursor(int by) {
        cursor += by;
    }

    public void decrementCursor(int by) {
        cursor -= by;
    }

    public int getCursor() {
        return cursor;
    }

    public Optional<String> getResult() {
        return Optional.ofNullable(result);
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getStackSize() {
        return stack.size();
    }

    private void checkLabelUnique(String label) {
        if (labels.containsKey(label)) {
            throw new IllegalStateException("Label " + label + " is not unique");
        }
    }
}
