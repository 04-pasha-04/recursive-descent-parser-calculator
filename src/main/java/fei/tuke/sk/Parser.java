package fei.tuke.sk;


public class Parser {
    private Lexer lexer;

    public Parser(String input) {
        lexer = new Lexer(input);
    }

    private int E() {
        return A();
    }


    private int A() {
        int result = M();
        while (lexer.hasNext()) {
            char op = lexer.peekNextChar();
            if (op == '+' || op == '-') {
                lexer.nextChar();
                int right = M();
                if (op == '+') {
                    result += right;
                } else {
                    result -= right;
                }
            } else {
                break;
            }
        }
        return result;
    }


    private int M() {
        int result = L();
        while (lexer.hasNext()) {
            char op = lexer.peekNextChar();
            if (op == '*' || op == '/') {
                lexer.nextChar();
                int right = L();
                if (op == '*') {
                    result *= right;
                } else if (right != 0) {
                    result /= right;
                } else {
                    throw new IllegalArgumentException("Division by zero");
                }
            } else {
                break;
            }
        }
        return result;
    }


    private int L() {
        int result = F();
        while (lexer.hasNext() && lexer.peekNextChar() == '&') {
            lexer.nextChar();
            int right = F();
            result = (result != 0 && right != 0) ? 1 : 0;
        }
        return result;
    }

    private int F() {
        if (lexer.hasNext() && lexer.peekNextChar() == '-') {
            lexer.nextChar();
            return -G();
        } else {
            return G();
        }
    }

    private int G() {
        if (lexer.hasNext() && Character.isDigit(lexer.peekNextChar())) {
            int result = 0;
            while (lexer.hasNext() && Character.isDigit(lexer.peekNextChar())) {
                result = result * 10 + (lexer.nextChar() - '0');
            }
            return result;
        } else if (lexer.hasNext() && lexer.peekNextChar() == '(') {
            lexer.nextChar();
            int result = E();
            if (lexer.hasNext() && lexer.peekNextChar() == ')') {
                lexer.nextChar();
                return result;
            } else {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
        } else {
            throw new IllegalArgumentException("Invalid expression");
        }
    }

    public static int evaluate(String expr) {
        Parser parser = new Parser(expr);
        int result = parser.E();
        if (parser.lexer.hasNext()) {
            throw new IllegalArgumentException("Invalid expression");
        }
        return result;
    }
}

