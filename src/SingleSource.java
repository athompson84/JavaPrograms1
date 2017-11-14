/*
* Anthony Thompson
* Programming assignment
* 11/3/17
*
*
*
*/
import java.io.*;
import java.util.*;


public class SingleSource {


    private GraphNode[] vertex;
    private int graphSize;
    private MPriorityQueue q;
    private static int totalCost;

    //constructor of Single Source Graph
    public SingleSource(int size) {
        this.graphSize = size;
        vertex = new GraphNode[size];
        addGraphNode();
        q = new MPriorityQueue(size);
    }

    //class for graph node
    public class GraphNode {

        int name;
        int Path_Cost;
        Neighbor neborList;
        State nodeState;

        GraphNode(int value) {
            this.name = value;
            nodeState = State.NEW;
            Path_Cost = Integer.MAX_VALUE;
        }
    }

    // class for neighbor vertex
    public class Neighbor {

        int nodeIndex;
        int edgeWeight;
        Neighbor next;

        public Neighbor(int i, Neighbor nearby, int w) {
            this.nodeIndex = i;
            this.next = nearby;
            this.edgeWeight = w;
        }
    }

    //function to add nodes
    private void addGraphNode() {
        for (int i = 1; i <= graphSize; i++) {
            addGraphNode(i);
        }
    }

    public void addGraphNode(int value) {
        vertex[value - 1] = new GraphNode(value);
    }

    // function to add edge
    public void addGraphEdge(int s, int d, int w) {
        int sIndex;
        if(s == 0){
            sIndex = 0;
        }else{
            //Source Index
            sIndex = s - 1;
        }

        //Destination Index
        int dIndex = d - 1;
        //Source Node
        GraphNode sNode = vertex[sIndex];
        //Destination Node
        GraphNode dNode = vertex[dIndex];
        sNode.neborList = new Neighbor(dIndex, sNode.neborList, w);
        dNode.neborList = new Neighbor(sIndex, dNode.neborList, w);
    }

    // function to calculate shortest path from source
    public void CalcShortestPath(int soucnam) {
        for (int i = 0; i < graphSize; i++) {
            if (vertex[i].name == soucnam) {
                Dijkstra(vertex[i]);
                break;
            }
        }
    }

    // citation for algorithm http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
    // dijkstra algorithm/BFS
    private void Dijkstra(GraphNode sourceNode) {

        q.add(sourceNode);

        sourceNode.nodeState = State.IN_QUEUE;
        sourceNode.Path_Cost = 0;

        while (!q.isEmpty()) {

            GraphNode vNode = q.remove();
            vNode.nodeState = State.SEEN;
            Neighbor c_Edge = vNode.neborList;

            while (c_Edge != null) {

                GraphNode neighborObj = vertex[c_Edge.nodeIndex];

                if (neighborObj.nodeState == State.NEW) {
                    q.add(neighborObj);
                    neighborObj.nodeState = State.IN_QUEUE;
                }

                //update the cost of each node we did not see
                if (neighborObj.nodeState != State.SEEN && ((c_Edge.edgeWeight + vNode.Path_Cost)
                        < neighborObj.Path_Cost)) {
                    neighborObj.Path_Cost = c_Edge.edgeWeight + vNode.Path_Cost;
                }
                c_Edge = c_Edge.next;
            }
        }

        //prints out the shortest distance
        for (int i = 0; i < graphSize; i++) {

            if (vertex[i].Path_Cost != Integer.MAX_VALUE) {
                System.out.println("distance from " + sourceNode.name + " to " + vertex[i].name + " is " + vertex[i].Path_Cost);
                totalCost += vertex[i].Path_Cost;
            } else {
                System.out.println(vertex[i].name + " is not reachable from " + sourceNode.name);
            }
        }
    }

    public enum State {
        NEW, IN_QUEUE, SEEN
    };

    /*
    *citation for binary heap
    *https://www.java-tips.org/java-se-tips-100019/24-java-lang/1906-priority-queue-binary-heap
    * -implementation-in-java.html
    */

    //binary heap using queue
    public class MPriorityQueue {

        GraphNode[] queue;
        int maxSize;
        int end = -1, start = -1;

        MPriorityQueue(int maxSize) {
            this.maxSize = maxSize;
            queue = new GraphNode[maxSize];
        }

        public void add(GraphNode node) {
            queue[++end] = node;
        }
        //function to remove node

        public GraphNode remove() {
            GraphNode minValueNode = null;
            int minValue = Integer.MAX_VALUE;
            int minIndex = -1;
            start++;

            for (int i = start; i <= end; i++) {
                if (queue[i].nodeState == State.IN_QUEUE
                        && queue[i].Path_Cost < minValue) {
                    minValue = queue[i].Path_Cost;
                    minValueNode = queue[i];
                    minIndex = i; //minimum value Index
                }
            }
            Swap(start, minIndex);
            queue[start] = null;
            return minValueNode;
        }
        //function to swap

        public void Swap(int i, int j) {
            GraphNode tmp = queue[i];
            queue[i] = queue[j];
            queue[j] = tmp;
        }

        public boolean isEmpty() {
            return start == end;
        }
    }


    public static void main(String[] args) throws FileNotFoundException {

        //Read input of the firstInput
        File firstInput = new File("firstInput.txt");

        //File secondInput = new File("secondInput.txt");

        //n = vertices and m = edges
        int n, m;
        String[] parts;

        //values to be used later
        int source, dest, destWeight;

        Scanner input = new Scanner(firstInput);

        //grab the first line of the file
        String scan = input.nextLine();

        /*split notation https://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java*/

        //Split the first line n= and m= by the space and save to array
        parts = scan.split(" ");

        //now we can save the split string into 2 different parts to get our n and m
        String set1 = parts[0];

        //1 holds m
        String set2 = parts[1];

        // split string from "=" from here we should get n and 25000
        String[] newParts1 = set1.split("=");

        // split string from "=" from here we should get m and 576014
        String[] newParts2 = set2.split("=");


        //http://javatutorialhq.com/java/lang/integer-class-tutorial/parseint-method-example/
        n = Integer.parseInt(newParts1[1]);
        m = Integer.parseInt(newParts2[1]);

        System.out.println("n = " + n);
        System.out.println("m = " + m);

        //create a graph with the number of vertices
        SingleSource g = new SingleSource(n);

        //Temp variables to test the values from the file
        int count = 0;
        source = 0;
        dest = 0;
        destWeight = 0;

        // Skip the first line since it is zero, We already set zero to the source
        scan = input.nextLine();

        while(input.hasNextLine()){
            scan = input.nextLine();
            parts = scan.split(" ");

            if(parts.length < 2){

                if(parts[0].equals("")){
                    continue;
                }
                source = Integer.parseInt(parts[0]);
            } else{
                for(int i = 0; i < parts.length; i++){

                    if(!parts[i].equals("") && count == 0){
                        dest = Integer.parseInt(parts[i]);
                        count++;
                    }else if(!parts[i].equals("") && count == 1){
                        destWeight = Integer.parseInt(parts[i]);
                        count--;
                    }
                }
            }

            //add the edge to the graph
            g.addGraphEdge(source, dest, destWeight);
        }

        input.close();
        g.CalcShortestPath(1);
        System.out.println("The total cost of the path = " + totalCost);
    }
}
