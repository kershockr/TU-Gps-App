package com.company;

public class Edge
{
    String name;
    public Node start, end, previous; //start and end nodes of an edge, previous node in shortest path
    public double weight; //weight of this edge


    public Edge()
    {
        start = null;
        end = null;
        weight = 0;
    }
    public Edge(String n, Node s, Node e, double w)
    {
        name = n;
        start = s;
        end = e;
        weight = w;
    }
    public void setWeight(double w)
    {
        weight = w;
    }
}
