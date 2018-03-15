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
            PrintWriter pw = new PrintWriter(fw);
            paths.setWritable(true);


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
            Edge TC = new Edge("TC", TRun, Corner, 254.4);
            Edge CT = new Edge("CT", Corner, TRun, 254.4);
            Edge CU = new Edge("CU", Corner, Union, 151.8);
            Edge UC = new Edge("UC", Union, Corner, 151.8);
            Edge CA = new Edge("CA", Corner, Art, 71.2);
            Edge AC = new Edge("AC", Art, Corner, 71.2);
            Edge UB = new Edge("UB", Union, Bus, 64.4);
            Edge BU = new Edge("BU", Bus, Union, 64.4);
            Edge AB = new Edge("AB", Art, Bus, 160);
            Edge BA = new Edge("BA", Bus, Art, 160);
            Edge BY = new Edge("BY", Bus, York, 323.4);
            Edge YB = new Edge("YB", York, Bus, 323.4);
            edges.add(TC);
            edges.add(CU);
            edges.add(CA);
            edges.add(UB);
            edges.add(AB);
            edges.add(BY);
            edges.add(CT);
            edges.add(UC);
            edges.add(AC);
            edges.add(BU);
            edges.add(BA);
            edges.add(YB);



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
                        String path = s.name + "/" + d.name + ":     " + graph.getPathTo(d, s);
                        System.out.println("Writing path to file: " + path);
                        fw.write(path + "\n");
                    }
                }
            }
            fw.close();
        } catch (Exception e) {System.out.println(e);}


    }
}


