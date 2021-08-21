package sr.unasat.money.exchange.simulator.datastructures.implementation;

import sr.unasat.money.exchange.simulator.datastructures.adt.DfsGraph;
import sr.unasat.money.exchange.simulator.datastructures.adt.List;
import sr.unasat.money.exchange.simulator.datastructures.adt.Stack;
import sr.unasat.money.exchange.simulator.entities.Vertex;

public class DfsGraphImpl implements DfsGraph {

    private final int MAX_VERTS = 20;
    //private Vertex vertexList[]; // list of vertices
    private List<Vertex> vertexList;
    private double adjMat[][];      // adjacency matrix
    private int nVerts;          // current number of vertices
    private Stack theStack;

    // ------------------------------------------------------------
    public DfsGraphImpl()               // constructor
    {
        vertexList = new ArrayList<>();
        // adjacency matrix
        adjMat = new double[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int y = 0; y < MAX_VERTS; y++)      // set adjacency
            for (int x = 0; x < MAX_VERTS; x++)   //    matrix to 0
                adjMat[x][y] = 0;
        theStack = new StackDfsImpl();
    }  // end constructor


    @Override
    public void addVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
        this.nVerts = vertexList.size();
    }

    // ------------------------------------------------------------
    @Override
    public void addVertex(Vertex lab) {
        nVerts++;
        vertexList.add(lab);
    }

    // ------------------------------------------------------------
    @Override
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
    }

    @Override
    public double[][] getEdges() {
        return this.adjMat;
    }

    @Override
    public void setEdges(double[][] adjMat) {
        this.adjMat = adjMat;
    }

    // ------------------------------------------------------------
    @Override
    public void displayParentVertex(int vertexIndex) {
        System.out.println("already visited :" + vertexList.get(vertexIndex).getLocatie());
    }

    @Override
    public boolean displayVertexWithDestination(int vertexIndex, int destinationIndex) {

        System.out.println("          We can exchange our money whilst traveling to\n  -> adjacent unvisited location :" + vertexList.get(vertexIndex).getLocatie());

        if (vertexList.get(vertexIndex).getLocatie() == vertexList.get(destinationIndex).getLocatie()) {
            System.out.print((" It seems that we've reached our destination location :" + vertexList.get(vertexIndex).getLocatie()));
            return true;
        }
        return false;
    }

    // ------------------------------------------------------------
    @Override
    public void dfs(int startingIndex, int endingIndex)  // depth-first search
    {                                 // begin at vertex 0
        vertexList.get(startingIndex).setInTree(true);  // mark it
        System.out.print("Starting location And ");
        displayParentVertex(startingIndex);                 // display it
        theStack.push(startingIndex);                 // push it


        while (!theStack.isEmpty())      // until stack empty,
        {
            // get an unvisited vertex adjacent to stack top
            int possibleAdjacentVertex = getAdjUnvisitedVertex(theStack.peek());
            if (possibleAdjacentVertex == -1)                    // if no such vertex,
            {
              int currentVertex = theStack.pop();
                System.out.println("   There are no more unvisited adjacent locations I could travel to from " + vertexList.get(currentVertex).getLocatie() +" (so we travel a location back)");
            }

            else                           // if it exists,
            {
                vertexList.get(possibleAdjacentVertex).setInTree(true);  // mark it
                if (displayVertexWithDestination(possibleAdjacentVertex, endingIndex)) {
                    break;
                }                 // display it
                theStack.push(possibleAdjacentVertex);                 // push it
            }
        }  // end while

        // stack is empty, so we're done
        for (int j = 0; j < nVerts; j++)          // reset flags
            vertexList.get(j).setInTree(false);
    }  // end dfs

    // ------------------------------------------------------------
    // returns an unvisited vertex adj to v
    @Override
    public int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < nVerts; j++)
            if (adjMat[v][j] == 1 && vertexList.get(j).isInTree() == false)
                return j;
        return -1;
    }  // end getAdjUnvisitedVertex()
// ------------------------------------------------------------


/*    public static void main(String[] args)
    {
        // Create a sample graph
        DfsGraphImpl g = new DfsGraphImpl(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(2, 0);
        g.addEdge(2, 1);
        g.addEdge(1, 3);

        // arbitrary source
        int s = 2;

        // arbitrary destination
        int d = 3;

        System.out.println(
                "Following are all different paths from "
                        + s + " to " + d);
        g.printAllPaths(s, d);
    }*/

}
