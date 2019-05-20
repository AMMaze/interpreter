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
            Assert.assertEquals("Result: 2", baos.toString().trim());
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
            Assert.assertEquals("Result: 0", baos.toString().trim());
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
            Assert.assertEquals("Result: 2", baos.toString().trim());
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
            Assert.assertEquals("Result: 1", baos.toString().trim());
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
            Assert.assertEquals("Result: 1", baos.toString().trim());
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
            Assert.assertEquals("Result: 0", baos.toString().trim());
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
            Assert.assertEquals("Result: 1", baos.toString().trim());
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
            Assert.assertEquals("Result: 1", baos.toString().trim());
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
            System.setIn(new ByteArrayInputStream("(-1)".getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            App.main(null);
            Assert.assertEquals("Result: -1", baos.toString().trim());
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
            Assert.assertEquals("Result: 1", baos.toString().trim());
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
            Assert.assertEquals("Result: -14", baos.toString().trim());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }
}
