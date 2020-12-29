package kboss.app;

import static org.junit.Assert.*;

import org.junit.Test; 

public class HaffmanTest {
    HaffmanTree haffman;
    //@Test
    public String encodeTest(){
        String charWeightPairs = "(a,1),(b,2.0),(c,3),(d,4)";
        haffman = new HaffmanTreeImpl(charWeightPairs);
        String a = haffman.encode("a");
        String b = haffman.encode("b");
        String c = haffman.encode("c");
        String d = haffman.encode("d");
        String all = haffman.encode("abcd");
        return c;
    }
    //@Test
    public String decodeTest(){
        String charWeightPairs = "(a,1),(b,2.0),(c,3),(d,4)";
        haffman = new HaffmanTreeImpl(charWeightPairs);
        String a = haffman.decode("110");
        String b = haffman.decode("111");
        String c = haffman.decode("10");
        String d = haffman.decode("0");
        String abcd = haffman.decode("110111100");
        return abcd;
    }
 
    public String showTreeTest(){
        String charWeightPairs = "(a,1),(b,2.0),(c,3),(d,4)";
        haffman = new HaffmanTreeImpl(charWeightPairs);
        String a = haffman.showTree();
        return a;
    } 
    public static void main(String[] args) {
        new HaffmanTest().showTreeTest();
        new HaffmanTest().decodeTest();
        new HaffmanTest().encodeTest();
    }
}
