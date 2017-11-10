import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class GraphCls {
    private NodeCls[] vertticees;
    private int sizze_of_grph;
    private MinProrityQue queuue;
    //contructor of GraphCls
    public GraphCls(int sizze_of_grph) {
        this.sizze_of_grph = sizze_of_grph;
        vertticees = new NodeCls[sizze_of_grph];
        addNodFunc();
        queuue = new MinProrityQue(sizze_of_grph);
    }
    //class for graph node
    public class NodeCls {
        int name_var;
        int cost;
        NighborCls neighbourList;
        State state;
        NodeCls(int name_var) {
            this.name_var = name_var;
            state = State.NEW;
            cost = Integer.MAX_VALUE;
        }
    }
    // class for neighbour
    public class NighborCls {
        int innddexx;
        int weightt;
        NighborCls next;
        public NighborCls(int innddexx, NighborCls next, int
                weightt) {
            this.innddexx = innddexx;
            this.next = next;
            this.weightt = weightt;
        }
    }
    //function to add nodes
    private void addNodFunc() {
        for (int i = 1; i <= sizze_of_grph; i++) {
            addNodeFunc(i);
        }
    }
    public void addNodeFunc(int name_var) {
        vertticees[name_var - 1] = new NodeCls(name_var);
    }
    // function to add edge
    public void adddEddgeFunc(int sorcnam, int destnname, int
            weightt) {
        int srcIndex = sorcnam - 1;
        int destiIndex = destnname - 1;
        NodeCls srcNode = vertticees[srcIndex];
        NodeCls destiNode = vertticees[destiIndex];
        srcNode.neighbourList = new NighborCls(destiIndex,
                srcNode.neighbourList, weightt);
        destiNode.neighbourList = new NighborCls(srcIndex,
                destiNode.neighbourList, weightt);
    }
    // function to calculate shortest path from source
    public void ComputeShortestPath(int soucnam) {
        for (int i = 0; i < sizze_of_grph; i++) {
            if (vertticees[i].name_var == soucnam) {
                applyDijkstraAlgorithm(vertticees[i]);
                break;
            }
        }
    }
    // dijistras algorithm using BFS
    private void applyDijkstraAlgorithm(NodeCls sourceNode) {
        queuue.add(sourceNode);
        sourceNode.state = State.IN_Q;
        sourceNode.cost = 0;
        while (!queuue.isEmmptty()) {
            NodeCls visitedNode = queuue.remove();
            visitedNode.state = State.VISITED;
            NighborCls connectedEdge =
                    visitedNode.neighbourList;
            while (connectedEdge != null) {
                NodeCls nighborobj =
                        vertticees[connectedEdge.innddexx];
                if (nighborobj.state == State.NEW) {
                    queuue.add(nighborobj);
                    nighborobj.state = State.IN_Q;
                }
                //updating cost of each non visited nodes
                if (nighborobj.state != State.VISITED &&
                        ((connectedEdge.weightt + visitedNode.cost) <
                                nighborobj.cost)) {
                    nighborobj.cost = connectedEdge.weightt +
                            visitedNode.cost;
                    nighborobj.cost = connectedEdge.weightt +
                            visitedNode.cost;
                }
                connectedEdge = connectedEdge.next;
            }
        }

        //printing shortest distances
        for(int i = 0; i < sizze_of_grph; i++){
            if(vertticees[i].cost != Integer.MAX_VALUE){
                System.out.println("distance from " +sourceNode.name_var + " to " +vertticees[i].name_var+ " is " +vertticees[i].cost);
            }else{
                System.out.println(vertticees[i].name_var +" is not reachable from "+sourceNode.name_var);
            }
        }
    }
    public enum State {
        NEW, IN_Q, VISITED
    };
    //binary heap implementation using queue
    public class MinProrityQue {
        NodeCls[] queuue;
        int maxSize;
        int reaarr = -1, fronnt = -1;
        MinProrityQue(int maxSize) {
            this.maxSize = maxSize;
            queuue = new NodeCls[maxSize];
        }
        public void add(NodeCls node) {
            queuue[++reaarr] = node;
        }
        //function to remove node
        public NodeCls remove() {
            NodeCls minValuedNode = null;
            int minnvallu = Integer.MAX_VALUE;
            int minnvalluIndex = -1;
            fronnt++;
            for (int i = fronnt; i <= reaarr; i++) {
                if (queuue[i].state == State.IN_Q &&
                        queuue[i].cost < minnvallu) {
                    minnvallu = queuue[i].cost;
                    minValuedNode = queuue[i];
                    minnvalluIndex = i;
                }
            }
            swwappFunc(fronnt, minnvalluIndex);
            queuue[fronnt] = null;
            return minValuedNode;
        }
        //function to swap
        public void swwappFunc(int inddxx_one, int inddxx_two) {
            NodeCls temp = queuue[inddxx_one];
            queuue[inddxx_one] = queuue[inddxx_two];
            queuue[inddxx_two] = temp;
        }
        public boolean isEmmptty() {
            return fronnt == reaarr;
        }
    }
    public static void main(String[] args) {
        //reading input file
        File fileobj = new File("inppt.txt");
        try {
            Scanner scobj = new Scanner(fileobj);
            //reading number of vertices from file
            int num_of_vertices=scobj.nextInt();
            //crating graph with number of vertices
            GraphCls graph = new GraphCls(num_of_vertices);
            //reading number of edges from file
            int num_of_edges=scobj.nextInt();
            //for each edge
            while (num_of_edges>0)
            {
                //add edge into graph
                graph.adddEddgeFunc(scobj.nextInt(),scobj.nextInt(),
                        scobj.nextInt());
                num_of_edges=num_of_edges-1;
            }
            scobj.close();
            //calling function to find shortest path
            graph.ComputeShortestPath(1);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

