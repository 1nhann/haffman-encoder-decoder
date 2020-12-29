package top.inhann.app.huffman;

import java.io.Serializable;

public class Char implements Serializable{
    Character c;
    Double weight;
    public Char(Character c, Double weight){
        this.c = c;
        this.weight = weight;
    }
}