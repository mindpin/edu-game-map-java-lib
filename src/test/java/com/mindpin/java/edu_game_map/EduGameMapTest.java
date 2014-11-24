package com.mindpin.java.edu_game_map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

/**
 * Unit test for simple App.
 */
public class EduGameMapTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public EduGameMapTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(EduGameMapTest.class);
    }

    public String getTestJsonPath(String filename) {
        URL url = getClass().getResource(filename);
        return url.getPath();
    }

    public String getTestJson(String filename) throws FileNotFoundException {
        File file = new File(getTestJsonPath(filename));
        return new Scanner(file).useDelimiter("\\Z").next();
    }

    public Map getMap(String filename){
        String json = null;
        try {
            json = getTestJson(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertNotNull("read json failure", json);
        Map map = Map.from_json(json);
        return map;
    }

    public void test_m1() {
        Map map = getMap("/m1.json");
        assertEquals(map.nodes.size(), 11);
        assertEquals(map.begin_node.id, "1");
    }

    public void test_m2() {
        Map map = getMap("/m2.json");
        assertEquals(map.nodes.size(), 4);
        assertEquals(map.begin_node.id, "2-1");
    }
}
