package codewars.whitespace.interpreter.tools;

import codewars.whitespace.interpreter.model.InterpreterState;

import java.io.InputStream;

public interface Interpreter {

    void interpret(String code, InterpreterState state, InputStream input);

}
