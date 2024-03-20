package fei.tuke.sk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void testSimpleAddition() {
        assertEquals(4, Parser.evaluate("2+2"));
    }

    @Test
    public void testSimpleSubtraction() {
        assertEquals(0, Parser.evaluate("2-2"));
    }

    @Test
    public void testSimpleMultiplication() {
        assertEquals(9, Parser.evaluate("3*3"));
    }

    @Test
    public void testSimpleDivision() {
        assertEquals(2, Parser.evaluate("4/2"));
    }

    @Test
    public void testSimpleLogicalAndTrue() {
        assertEquals(1, Parser.evaluate("1&1"));
    }

    @Test
    public void testSimpleLogicalAndFalse() {
        assertEquals(0, Parser.evaluate("1&0"));
    }

    @Test
    public void testMultipleLogicalAnd() {
        assertEquals(1, Parser.evaluate("1&1&1&1"));
    }

    @Test
    public void testMixedOperations() {
        assertEquals(9, Parser.evaluate("1+2*4"));
    }

    @Test
    public void testParentheses() { assertEquals(12, Parser.evaluate("(2+2)*3")); }

    @Test
    public void testNestedParentheses() { assertEquals(6, Parser.evaluate("2+(2&2*(2+2))")); }
    @Test
    public void testLogicalAndWithArithmetic() {
        assertEquals(2, Parser.evaluate("3&12*2"));
    }

    @Test
    public void testUnaryMinus() {
        assertEquals(-4, Parser.evaluate("-2*2"));
    }

    @Test
    public void testDivisionByZero() {
        assertThrows(IllegalArgumentException.class, () -> Parser.evaluate("1/0"));
    }

    @Test
    public void testInvalidExpression() {
        assertThrows(IllegalArgumentException.class, () -> Parser.evaluate("2+*2"));
    }

    @Test
    public void testComplexExpression() {
        assertEquals(9, Parser.evaluate("(3+12)*2&1&2-2*3"));
    }

    @Test
    public void testComplexExpressionRefactored() { assertEquals(9, Parser.evaluate("((3+12)*(2&(1&2)))-(2*3)")); }

    @Test
    public void testMismatchedParentheses() {
        assertThrows(IllegalArgumentException.class, () -> Parser.evaluate("(2+2)*3)"));
    }
}
