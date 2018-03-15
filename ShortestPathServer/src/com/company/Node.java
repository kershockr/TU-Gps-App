package com.company;
import java.util.*;


public class Node
{
    private static int count = 0;

    public String name; //for displaying and printing
    public int ID; //for sorting and parsing without dealing with strings
    ArrayList<Node> neighbors; //adjacent nodes to this node
    public Node previous = null; //previous node (for dijkstras)
    public double distance; // for use later?

    public Node()
    {
        name = "";
        ID = ++count;
    }
    public Node(String n)
    {
        name = n;
        ID = ++count;
    }
    public Node(String n, ArrayList<Node> adj)
    {
        name = n;
        ID = ++count;
        neighbors = adj;
    }


}
