package kboss.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kboss.dsaj.binTree.BinTree;
import kboss.dsaj.sequence.Sequence;
import kboss.dsaj.sequence.SequenceImpl;



public class App {

    private Sequence charSequence;
    private BinTree haffman;
    private BinTree defaultTree;

    public App(){
        try{
            BufferedReader in = new BufferedReader(new FileReader("data.txt"));
            String pairs = in.readLine();
            defaultTree = new HuffmanTreeBuilder().buildHuffmanTree(str2Sequence(pairs));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public BinTree initHaffman(String charWeightPairs){
        haffman = new HuffmanTreeBuilder().buildHuffmanTree(str2Sequence(charWeightPairs));
        return haffman;
    }

    public Sequence str2Sequence(String charWeightPairs){
        charSequence = new SequenceImpl();
        String regEx = "(.,[0-9.]+)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher m = pattern.matcher(charWeightPairs);
        List<String> pairs = new ArrayList<>();
        while(m.find() == true){
            pairs.add(m.group());
        }
        for(String pair:pairs){
            String[] s = pair.split(",");
            //Character c1 = s[0].substring(1).toCharArray()[0];
            Character c1 = s[0].toCharArray()[0];
            //Double weight = Double.parseDouble(s[1].substring(0, s[1].length()));
            Double weight = Double.parseDouble(s[1]);
            Char c = new CharImpl(c1, weight);
            charSequence.insertLast(c);
        }
        return charSequence;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String charWeightPairs = in.nextLine();
        in.close();
    }
}
