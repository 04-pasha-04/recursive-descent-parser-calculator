package fei.tuke.sk;

class Lexer {
    private String input;
    private int index;

    Lexer(String input) {
        this.input = input;
        this.index = 0;
    }

    boolean hasNext() {
        return index < input.length();
    }

    char nextChar() {
        if (hasNext()) {
            char currentChar = input.charAt(index++);
            if (isOperator(currentChar)) {
                if (index < input.length() && isOperator(input.charAt(index))) {
                    throw new IllegalArgumentException("Error: Two consecutive operators found at position " + (index - 1));
                }
            }
            return currentChar;
        } else {
            return '\0';
        }
    }

    char peekNextChar() {
        if (index < input.length()) {
            return input.charAt(index);
        } else {
            return '\0';
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '&' || c == '^';
    }
}