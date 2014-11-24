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

    public void test_nodes_map(){
        Map m1 = getMap("/m1.json");
        Map m2 = getMap("/m2.json");
        for (Node node : m1.nodes){
            assertEquals(node.map, m1);
        }
        for (Node node : m2.nodes){
            assertEquals(node.map, m2);
        }
    }

    public void test_nodes_jump_to_map(){
        Map m1 = getMap("/m1.json");
        Map m2 = getMap("/m2.json");
        for (Node node : m1.nodes){
            if(node.id.equals("10")) {
                assertEquals(node.jump_to_map(), m2);
            }
        }
        for (Node node : m2.nodes){
            if(node.id.equals("2-1")) {
                assertEquals(node.jump_to_map(), m1);
            }
        }
    }

    public void test_nodes_is_begin_node(){
        Map m1 = getMap("/m1.json");
        Map m2 = getMap("/m2.json");
        for (Node node : m1.nodes){
            if(node.id.equals("1")) {
                assertTrue(node.is_begin_node());
            }
            else{
                assertFalse(node.is_begin_node());
            }
        }
        for (Node node : m2.nodes){
            if(node.id.equals("2-1")) {
                assertTrue(node.is_begin_node());
            }
            else{
                assertFalse(node.is_begin_node());
            }
        }
    }

    public void test_nodes_parents_and_children(){
        Map m1 = getMap("/m1.json");
        Map m2 = getMap("/m2.json");
        assertEquals(m1.begin_node.parents.size(), 0);

        assertEquals(m1.hash_nodes.get("1").parents.size(), 0);
        assertEquals(m1.hash_nodes.get("2").parents.size(), 1); // 1
        assertEquals(m1.hash_nodes.get("3").parents.size(), 1); // 1
        assertEquals(m1.hash_nodes.get("4").parents.size(), 1); // 2
        assertEquals(m1.hash_nodes.get("5").parents.size(), 2); // 1, 2
        assertEquals(m1.hash_nodes.get("6").parents.size(), 1); // 3
        assertEquals(m1.hash_nodes.get("7").parents.size(), 1); // 3
        assertEquals(m1.hash_nodes.get("8").parents.size(), 2); // 4, 5
        assertEquals(m1.hash_nodes.get("9").parents.size(), 2); // 6 7
        assertEquals(m1.hash_nodes.get("10").parents.size(), 1); // 9
        assertEquals(m1.hash_nodes.get("11").parents.size(), 1); // 9

        assertEquals(m1.hash_nodes.get("1").children.size(), 3); // 2, 3, 5
        assertEquals(m1.hash_nodes.get("2").children.size(), 2); // 4, 5
        assertEquals(m1.hash_nodes.get("3").children.size(), 2); // 6, 7
        assertEquals(m1.hash_nodes.get("4").children.size(), 1); // 8
        assertEquals(m1.hash_nodes.get("5").children.size(), 1); // 8
        assertEquals(m1.hash_nodes.get("6").children.size(), 1); // 9
        assertEquals(m1.hash_nodes.get("7").children.size(), 1); // 9
        assertEquals(m1.hash_nodes.get("8").children.size(), 0);
        assertEquals(m1.hash_nodes.get("9").children.size(), 2); // 10, 11
        assertEquals(m1.hash_nodes.get("10").children.size(), 0);
        assertEquals(m1.hash_nodes.get("11").children.size(), 0);

        assertEquals(m2.begin_node.parents.size(), 0);

        assertEquals(m2.hash_nodes.get("2-1").parents.size(), 0); // 2-2, 2-3, 2-4
        assertEquals(m2.hash_nodes.get("2-2").parents.size(), 1); // 2-1
        assertEquals(m2.hash_nodes.get("2-3").parents.size(), 1); // 2-1
        assertEquals(m2.hash_nodes.get("2-4").parents.size(), 1); // 2-1

        assertEquals(m2.hash_nodes.get("2-1").children.size(), 3); // 2-2, 2-3, 2-4
        assertEquals(m2.hash_nodes.get("2-2").children.size(), 0);
        assertEquals(m2.hash_nodes.get("2-3").children.size(), 0);
        assertEquals(m2.hash_nodes.get("2-4").children.size(), 0);
    }
}
