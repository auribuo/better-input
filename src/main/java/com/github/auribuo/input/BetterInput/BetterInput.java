package com.github.auribuo.input.BetterInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InvalidClassException;
import java.util.HashMap;
import java.util.function.Function;

public final class BetterInput {

    public class InputResult {

        public InputResult(String input) {
            this.input = input;
            this.object = input;
        }

        private Object object;
        private final String input;

        public <T> T cast() throws ClassCastException {
            return (T) object;
        }

        public Object getObject() {
            return object;
        }

        public <T> InputResult parse(Class<T> as) throws InvalidClassException {
            var parser = parserMap.getOrDefault(as, null);
            if (parser == null) {
                throw new InvalidClassException(as.getName(), "No such parser for given class");
            }
            this.object = parser.apply(input);
            return this;
        }

        public String asString() {
            if (object instanceof String) {
                return (String) object;
            }
            return object.toString();
        }

    }

    public <T> void registerParserFunction(Class<T> clazz, Function<String, Object> fn) {
        parserMap.put(clazz, fn);
    }

    private final HashMap<Class<?>, Function<String, Object>> parserMap;

    private BetterInput() {
        parserMap = new HashMap<>();
        parserMap.put(String.class, str -> str);
    }

    private static BetterInput instance = null;

    public static BetterInput instance() {
        if (instance == null) {
            instance = new BetterInput();
        }
        return instance;
    }

    public InputResult readLine() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        var line = reader.readLine();
        return new InputResult(line);
    }

}
