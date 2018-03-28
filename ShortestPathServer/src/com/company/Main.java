package com.company;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.io.*;


public class Main
{

    public static void main(String[] args) throws FileNotFoundException
    {
        try
        {
            String filename = "C:/Users/timme/Documents/Github/TU-Gps-App/ShortestPathServer/paths.txt";
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
            Node CLA = new Node("CLA"); //placeholder, not real data
            Node Smith = new Node("Smith"); //placeholder
            Node Library = new Node("Library"); //placeholder
            Node FreedomSquare = new Node("Freedom Square"); //placeholder
            nodes.add(TRun);
            nodes.add(Corner);
            nodes.add(Union);
            nodes.add(Bus);
            nodes.add(Art);
            nodes.add(York);
            nodes.add(CLA);
            nodes.add(Smith);
            nodes.add(Library);
            nodes.add(FreedomSquare);

            ArrayList<Edge> edges = new ArrayList<Edge>();

            addEdgeUndirected(edges, TRun, Corner, 253.4);
            addEdgeUndirected(edges, Corner, Union, 151.8);
            addEdgeUndirected(edges, Corner, Art, 71.2);
            addEdgeUndirected(edges, Union, Bus, 64.4);
            addEdgeUndirected(edges, Art, Bus, 160);
            addEdgeUndirected(edges, Bus, York, 323.4);
            addEdgeUndirected(edges, CLA, Union, 141.2); //placeholder, not real data
            addEdgeUndirected(edges, Smith, CLA, 100.2); //placeholder, not real data
            addEdgeUndirected(edges, Smith, Union, 96); //placeholder
            addEdgeUndirected(edges, Library, Smith, 99.4); //placeholder
            addEdgeUndirected(edges, Library, York, 113); //placeholder
            addEdgeUndirected(edges, FreedomSquare, Library, 69.9); //placeholder
            addEdgeUndirected(edges, FreedomSquare, Smith, 55);

            Graph graph = new Graph(nodes, edges);

            //for every node n in nodes
            //bellman ford the graph using n as the start node
            //print the path to every other node to file in format startID/endID: path

            //if file is already populated with paths, skip
            if(paths.length() > 0)
            {
                System.out.println("File populated. Starting socket...");
            }
            else //otherwise, populate file with paths
            {
                System.out.println("Populating file..");
                for (int i = 0; i < nodes.size(); i++)
                {
                    Node s = nodes.get(i);
                    graph.BellmanFord(s);
                    //graph.printdists();
                    for (int j = 0; j < nodes.size(); j++)
                    {
                        Node d = nodes.get(j);
                        if (d != s)
                        {
                            String path = s.ID + "/" + d.ID + ": " + graph.getPathTo(d, s);
                            //System.out.println("Writing path to file: " + path);
                            fw.write(path + "\n");
                        }
                    }
                }
                fw.close();
            }


            //Test user requests
            /*
            //Get user source and destination
            System.out.print("Enter the int of the source node and dest node (Example: 2 4): ");
            int source = cin.nextInt();
            int dest = cin.nextInt();
            //validate user input
            if((source > nodes.size() || source < 1) || (dest > nodes.size() || dest < 1)) //nodes out of range
            {
                System.out.println("Index or source or dest out of range. Try again");
            }
            else
            {
                System.out.println("Getting path from " + source + " to " + dest);
                String userkey = source + "/" + dest; //The key we search for at the start of each line for a match
                String nextline; //string to store each line as we parse the file
                boolean found = false; //breaks loop when match is found
                while((nextline = bufferedReader.readLine()) != null && !found)
                {
                    int index = nextline.indexOf(userkey);
                    if(index >= 0) //returns negative value if key isn't found
                    {
                        found = true; //set break condition

                        System.out.println("Path: " + nextline.substring(4)); //print line after index 4 (x/x: key at start of line takes 4 chars)
                    }
                }

            }
            */

            //Socket pseudocode
            //while true
            //accept socket connection
            //key = user key from network stream
            //find path and store in string (as seen above)
            //send path back over network stream
            //close connection
            try
            {
                ServerSocket serverSocket = new ServerSocket(5252); //start the server on port 8888
                System.out.println("Socket started on port 5252. Waiting to accept client");
                Socket clientSocket = serverSocket.accept(); //accept a client
                System.out.println("Client accepted!");
                PrintWriter serverOut = new PrintWriter(clientSocket.getOutputStream(), true); //create output stream
                BufferedReader serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //create input strea,
                String userkey = Integer.parseInt(serverIn.readLine()) + "/" + Integer.parseInt(serverIn.readLine());

                String nextline; //string to store each line as we parse the file
                boolean found = false; //breaks loop when match is found
                while((nextline = bufferedReader.readLine()) != null && !found)
                {
                    int index = nextline.indexOf(userkey);
                    if(index >= 0) //returns negative value if key isn't found
                    {
                        found = true; //set break condition

                        //System.out.println("Path: " + nextline.substring(4)); //print line after index 4 (x/x: key at start of line takes 4 chars)
                        serverOut.println("Path: " + nextline.substring(4));
                    }
                }


            } catch (Exception e){System.out.println(e);}

        } catch (Exception e) {System.out.println(e);}


    }

    public static void addEdgeUndirected(ArrayList<Edge> list, Node source, Node dest, double weight)
    {
        list.add(new Edge(source, dest, weight));
        list.add(new Edge(dest, source, weight));
    }
}


