package top.inhann.app.huffman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import top.inhann.dsa.list.dlinkedImpl.ListImpl;
import top.inhann.dsa.sequence.Sequence;
import top.inhann.dsa.sequence.SequenceImpl;

public class App {
    private Sequence charSequence;
    private HaffmanTree1 haffman;
    public App(String charWeightPairs){
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
            Char c = new Char(c1, weight);
            charSequence.insertLast(c);
        }
        haffman = new HaffmanTree1(charSequence);
    }
    public void display(){
        haffman.display();
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String charWeightPairs = in.nextLine();
        in.close();
        App a = new App(charWeightPairs);
        a.display();
    }
}
