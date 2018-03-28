package com.company;

import java.util.*;

//on startup of server, go through every node and calculate the shortest path for each
//this can later be optimized by eliminated redundancies (path from B to A is reverse of A to B)
//can further be optimized by using subpaths (for example, the shortest path from B to C is withing A > B > C > D)
//only needs to be done once when the server starts up (but may need to be redone when new nodes are added)
//these can be stored in a database so even if the server goes offline, we don't need to do the calculations again


public class Graph
{
    public ArrayList<Node> nodes; //list of nodes in the graph
    public ArrayList<Edge> edges; //list of edges in the graph

    public Graph()
    {
        nodes = new ArrayList<Node>();
    }
    public Graph(ArrayList<Node> n, ArrayList<Edge> e)
    {
        nodes = n;
        edges = e;
    }

    public void BellmanFord(Node S) //calculating shortest paths
    {
        int n1 = nodes.indexOf(S);
        for(int i = 0; i < nodes.size(); i++)
        {
            nodes.get(i).distance = 1000000;
        }
        nodes.get(n1).distance = 0;
        nodes.get(n1).previous = null;
        for(int j = 0; j < nodes.size() - 1; j++)
        {
            for(int k = 0; k < edges.size(); k++)
            {
                if(edges.get(k).n2.distance > edges.get(k).n1.distance + edges.get(k).weight)
                {
                    edges.get(k).n2.distance = edges.get(k).n1.distance + edges.get(k).weight;
                    edges.get(k).n2.previous = edges.get(k).n1;
                }
            }
        }
        for(int l = 0; l < edges.size(); l++)
        {
            if(edges.get(l).n2.distance > edges.get(l).n1.distance + edges.get(l).weight)
            {
                System.out.println("Negative cycle in graph found");
            }
        }
    }

    public void printPath()
    {
        for (int i = nodes.size() -1; i < nodes.size(); i++)
        {
            System.out.println(nodes.get(i).name + ", distance: " + nodes.get(i).distance / 60 + " minutes");

            Node n = nodes.get(i);
            System.out.print(" Path: " + n.name);
            while (n.previous != null)
            {
                System.out.print(" <- " + n.previous.name);
                n = n.previous;
            }
            System.out.println();
        }
    }

    //debug
    public void printdists()
    {
        for(int i = 0; i < nodes.size(); i++)
        {
            Node n = nodes.get(i);
            System.out.println(n.name + " distance: " + n.distance);
        }
    }

    public String getPathTo(Node d, Node s)
    {
        Node dest = d;
        String path = "" + dest.name; //create an string with the ID of the final node
        while(dest.previous != null)
        {
            path = dest.previous.name + ", " + path;
            dest = dest.previous;
        }
        return path;
    }
}
