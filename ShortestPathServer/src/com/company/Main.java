package com.company;

import java.util.*;
import java.io.*;

public class Main
{

    public static void main(String[] args) throws FileNotFoundException
    {
        try
        {
            String filename = "C:/Users/timme/IdeaProjects/ShortestPathServer/paths.txt";
            File paths = new File(filename);
            FileWriter fw = new FileWriter(paths, true);
            FileReader fr = new FileReader(paths);
            BufferedReader bufferedReader = new BufferedReader(fr);
            paths.setWritable(true);

            Scanner cin = new Scanner(System.in);


            ArrayList<Node> nodes = new ArrayList<Node>();
            Node TRun = new Node("TRun");
            Node Art = new Node("Art");
            Node Corner = new Node("Corner");
            Node Bus = new Node("Bus");
            Node York = new Node("York");
            Node Union = new Node("Union");
            nodes.add(TRun);
            nodes.add(Corner);
            nodes.add(Union);
            nodes.add(Bus);
            nodes.add(Art);
            nodes.add(York);

            ArrayList<Edge> edges = new ArrayList<Edge>();

            addEdgeUndirected(edges, TRun, Corner, 253.4);
            addEdgeUndirected(edges, Corner, Union, 151.8);
            addEdgeUndirected(edges, Corner, Art, 71.2);
            addEdgeUndirected(edges, Union, Bus, 64.4);
            addEdgeUndirected(edges, Art, Bus, 160);
            addEdgeUndirected(edges, Bus, York, 323.4);

            Graph graph = new Graph(nodes, edges);

            //for every node n in nodes
            //bellman ford the graph using n as the start node
            //print the path to every other node to file in format startID/endID: path
            for(int i = 0; i < nodes.size(); i++)
            {
                Node s = nodes.get(i);
                graph.BellmanFord(s);
                //graph.printdists();
                for(int j = 0; j < nodes.size(); j++)
                {
                    Node d = nodes.get(j);
                    if(d != s)
                    {
                        String path = s.ID + "/" + d.ID + ":     " + graph.getPathTo(d, s);
                        System.out.println("Writing path to file: " + path);
                        fw.write(path + "\n");
                    }
                }
            }
            fw.close();

            System.out.print("Enter the int of the source node and dest node (Example: 2 4): ");
            int source = cin.nextInt();
            int dest = cin.nextInt();
            if((source > nodes.size() || source < 1) || (dest > nodes.size() || dest < 1)) //nodes out of range
            {
                System.out.println("Index or source or dest out of range. Try again");
            }
            else
            {
                System.out.println("Getting path from " + source + " to " + dest);
                String userkey = source + "/" + dest;
                String nextline;
                boolean found = false;
                while((nextline = bufferedReader.readLine()) != null && !found)
                {
                    int index = nextline.indexOf(userkey);
                    if(index >= 0)
                    {
                        found = true;
                        System.out.println(nextline.substring(index));
                    }
                }

            }
        } catch (Exception e) {System.out.println(e);}


    }

    public static void addEdgeUndirected(ArrayList<Edge> list, Node source, Node dest, double weight)
    {
        list.add(new Edge(source, dest, weight));
        list.add(new Edge(dest, source, weight));
    }
}


