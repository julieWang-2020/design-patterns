package com.wzh.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2020-06-04 21:21
 */
public class CompositeMain {

    public static void main(String[] args) {

        BranchNode  root=new BranchNode("root");
        BranchNode  chapter1=new BranchNode("chapter1");
        BranchNode  chapter2=new BranchNode("chapter2");
        Node c11=new LeafNode("c11");
        Node c12=new LeafNode("c12");
        BranchNode  section21=new BranchNode("section21");
        Node c211=new LeafNode("c211");
        Node c212=new LeafNode("c212");

        root.add(chapter1);
        root.add(chapter2);
        chapter1.add(c11);
        chapter1.add(c12);
        chapter2.add(section21);
        section21.add(c211);
        section21.add(c212);

        tree(root,0);
    }

    private static void tree(Node n,int depth) {
        for(int i=0; i<depth; i++) System.out.print("--");
        n.p();

        if(n instanceof BranchNode){
            ((BranchNode)n).nodes.forEach( o -> {
                tree(o,depth+1);
            });
        }
    }

}

abstract class Node{

    abstract public void p();
}


class LeafNode extends Node{

    String content;

    public LeafNode(String content) {
        this.content = content;
    }

    @Override
    public void p() {
        System.out.println(content);
    }
}



class BranchNode extends Node{

    String name;

    List<Node> nodes=new ArrayList<>();

    public BranchNode(String name) {
        this.name = name;
    }

    @Override
    public void p() {
        System.out.println(name);
    }

    public void add(Node n){
        nodes.add(n);
    }

    public void print(){

    }
}

