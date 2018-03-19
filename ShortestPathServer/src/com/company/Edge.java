package com.company;

public class Edge
{
    public Node n1, n2; //n1 and n2 nodes of an edge, previous node in shortest path
    public double weight; //weight of this edge


    public Edge()
    {
        n1 = null;
        n2 = null;
        weight = 0;
    }
    public Edge(Node s, Node e, double w)
    {
        n1 = s;
        n2 = e;
        weight = w;
    }
    public void setWeight(double w)
    {
        weight = w;
    }
}
