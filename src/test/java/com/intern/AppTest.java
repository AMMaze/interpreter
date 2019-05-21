package com.intern;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testBinary() {
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        try {
            System.setIn(new ByteArrayInputStream("(1+1)".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("2", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }

        try {
            System.setIn(new ByteArrayInputStream("(1-1)".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("0", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }

        try {
            System.setIn(new ByteArrayInputStream("(1*2)".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("2", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }

        try {
            System.setIn(new ByteArrayInputStream("(2/2)".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("1", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }

        try {
            System.setIn(new ByteArrayInputStream("(1%2)".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("1", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }

        try {
            System.setIn(new ByteArrayInputStream("(1>1)".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("0", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }

        try {
            System.setIn(new ByteArrayInputStream("(1<2)".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("1", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }

        try {
            System.setIn(new ByteArrayInputStream("(1=1)".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("1", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }

    @Test
    public void testUnary() {
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        try {
            System.setIn(new ByteArrayInputStream("-1".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("-1", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }

    @Test
    public void testBraces() {
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        try {
            System.setIn(new ByteArrayInputStream("((((1))))".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("1", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }

    @Test
    public void testExpression() {
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        try {
            System.setIn(new ByteArrayInputStream("(1-((3+2)*3))".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("-14", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }

    @Test
    public void testPriority() {
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        try {
            System.setIn(new ByteArrayInputStream("((2+3)<4)".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("0", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }

        try {
            System.setIn(new ByteArrayInputStream("(-5>4)".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("0", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }

    @Test
    public void testConditional() {
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        try {
            System.setIn(new ByteArrayInputStream("[(3>2)]?{(1+3)}:{(4*3)}".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("4", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }

    @Test
    public void testSyntaxError() {
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        try {
            System.setIn(new ByteArrayInputStream("$".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("SYNTAX ERROR", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }

        try {
            System.setIn(new ByteArrayInputStream("(-1))".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("SYNTAX ERROR", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }

        try {
            System.setIn(new ByteArrayInputStream("((-1)".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("SYNTAX ERROR", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }

    @Test
    public void testFunctions() {
        InputStream stdin = System.in;
        PrintStream stdout = System.out;
        try {
            System.setIn(new ByteArrayInputStream
                    (("f(x)={(x*10)}\n" +
                            "g(x)={(f(x)+f((x/2)))}\n" +
                            "g(10)").getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("150", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }

}
