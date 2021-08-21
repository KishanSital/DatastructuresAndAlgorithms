package sr.unasat.money.exchange.simulator.datastructures.implementation;

import sr.unasat.money.exchange.simulator.datastructures.adt.BfsGraph;
import sr.unasat.money.exchange.simulator.datastructures.adt.List;
import sr.unasat.money.exchange.simulator.datastructures.adt.QueueBfs;
import sr.unasat.money.exchange.simulator.entities.Vertex;

public class BfsGraphImpl implements BfsGraph {

    private final int MAX_VERTS = 20;
    //   private Vertex vertexList[]; // list of vertices
    private List<Vertex> vertexList;
    private double adjMat[][];      // adjacency matrix
    private int nVerts;          // current number of vertices
    private QueueBfs theQueue;

    // ------------------------------------------------------------
    public BfsGraphImpl()               // constructor
    {
//        vertexList = new Vertex[MAX_VERTS];
        vertexList = new ArrayList<>();
        // adjacency matrix
        adjMat = new double[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int rowIndex = 0; rowIndex < MAX_VERTS; rowIndex++)      // set adjacency
        {
            for (int columnIndex = 0; columnIndex < MAX_VERTS; columnIndex++)   //    matrix to 0
            {
                adjMat[rowIndex][columnIndex] = 0;

            }
        }

        theQueue = new QueueBfsImpl();
    }  // end constructor

    // -------------------------------------------------------------


    @Override
    public void addVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
        this.nVerts = vertexList.size();
    }

    @Override
    public void addVertex(Vertex lab) {
        nVerts++;
        vertexList.add(lab);
    }

    // -------------------------------------------------------------
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

    // -------------------------------------------------------------

    @Override
    public void displayParentVertex(int vertexIndex) {
        System.out.println("already visited :" + vertexList.get(vertexIndex).getLocatie());

    }


    @Override
    public void displayAdjacentVertex(int vertexIndex) {
//        System.out.print(vertexList[v].label);

        System.out.println("          We exchange our money whilst traveling to\n  -> adjacent unvisited location :" + vertexList.get(vertexIndex).getLocatie());
    }

    // -------------------------------------------------------------
    @Override
    public void bfs(int startingPosition)                   // breadth-first search
    {                                // begin at vertex 0
        vertexList.get(startingPosition).setInTree(true); // mark it
        //displayVertex(0);                // display it
        System.out.print("Starting location And ");
        theQueue.insert(startingPosition);              // insert at tail
        int possibleAdjacentVertex;
        while (!theQueue.isEmpty())     // until queue empty,
        {
            int currentVertex = theQueue.remove();   // remove vertex at head
            // until it has no unvisited neighbors
            displayParentVertex(currentVertex);
            while ((possibleAdjacentVertex = getAdjUnvisitedVertex(currentVertex)) != -1) {                                  // get one,
                vertexList.get(possibleAdjacentVertex).setInTree(true);   // mark it
                displayAdjacentVertex(possibleAdjacentVertex);                 // display it
                theQueue.insert(possibleAdjacentVertex);// insert it
            }   // end while
            if ((possibleAdjacentVertex = getAdjUnvisitedVertex(currentVertex)) == -1) {
                System.out.println("   We've visited the above mentioned locations, there are no more unvisited adjacent locations I could travel to from " + vertexList.get(currentVertex).getLocatie());
            }
            System.out.println();

        }  // end while(queue not empty)

        // queue is empty, so we're done
        for (int vertexIndex = 0; vertexIndex < nVerts; vertexIndex++)             // reset flags
        {
            vertexList.get(vertexIndex).setInTree(false);

        }
    }  // end bfs()

    // -------------------------------------------------------------
    // returns an unvisited vertex adj to v
    @Override
    public int getAdjUnvisitedVertex(int vertexRowIndex) {
        for (int vertexIndex = 0; vertexIndex < nVerts; vertexIndex++) {
            if (adjMat[vertexRowIndex][vertexIndex] == 1 && vertexList.get(vertexIndex).isInTree() == false)
                return vertexIndex;
        }
        return -1;
    }  // end getAdjUnvisitedVertex()

}
