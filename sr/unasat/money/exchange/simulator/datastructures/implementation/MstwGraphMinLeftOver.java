package sr.unasat.money.exchange.simulator.datastructures.implementation;

import sr.unasat.money.exchange.simulator.datastructures.adt.List;
import sr.unasat.money.exchange.simulator.datastructures.adt.MstwGraph;
import sr.unasat.money.exchange.simulator.datastructures.adt.PriorityQueue;
import sr.unasat.money.exchange.simulator.entities.Edge;
import sr.unasat.money.exchange.simulator.entities.Vertex;
import sr.unasat.money.exchange.simulator.entities.Weight;

public class MstwGraphMinLeftOver implements MstwGraph {

    private final int MAX_VERTS = 20;
    private final int INFINITY = 1000000;
    public double total;
    private double priceOfOneLiterInSRD;
    private double mileagePerLiter;
    private double moneyToExchangeFromSRDToUSD;
    private double hourlyNetIncomeInSRD;
    //    private Vertex vertexList[]; // list of vertices
    private List<Vertex> vertexList; // list of vertices
    private double adjMat[][];      // adjacency matrix
    private int nVerts;          // current number of vertices
    private int currentVert;
    private PriorityQueue thePQ;
    private int nTree;           // number of verts in tree

    // -------------------------------------------------------------
    public MstwGraphMinLeftOver(double priceOfOneLiterInSRD,
                                double moneyToExchangeFromUSDToSRD,
                                double hourlyNetIncomeInSRD,
                                double mileagePerLiter) {

        this.priceOfOneLiterInSRD = priceOfOneLiterInSRD;
        this.moneyToExchangeFromSRDToUSD = moneyToExchangeFromUSDToSRD;
        this.hourlyNetIncomeInSRD = hourlyNetIncomeInSRD;
        this.mileagePerLiter = mileagePerLiter;

        vertexList = new ArrayList<>(MAX_VERTS);
        // adjacency matrix
        adjMat = new double[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int rowIndex = 0; rowIndex < MAX_VERTS; rowIndex++)      // set adjacency
        {
            for (int columnIndex = 0; columnIndex < MAX_VERTS; columnIndex++)   //    matrix to 0
                adjMat[rowIndex][columnIndex] = INFINITY;
        }

        thePQ = new PriorityQueueImpl();
    }  // end constructor

    // -------------------------------------------------------------
    public void addVertex(Vertex vertex) {
        nVerts++;
//        vertexList[nVerts++] = new Vertex(lab);
        vertexList.add(vertex);
    }

    // -------------------------------------------------------------
    public void addEdge(int start, int end, Weight weightFactor) {


        adjMat[start][end] = weightFactor.calculateWeightOfLeftOverMoneyAfterExchangeAndExpensesInUSD(mileagePerLiter,
                priceOfOneLiterInSRD,
                hourlyNetIncomeInSRD,
                moneyToExchangeFromSRDToUSD);

        System.out.println();

        System.out.print(" Expenses : USD " + weightFactor.calculateWeightOfExpensesInUSD(mileagePerLiter,
                priceOfOneLiterInSRD,
                hourlyNetIncomeInSRD));

        System.out.print("\n Money after exchange and taking expenses into account: (total money USD "
                +
                (weightFactor.calculateWeightOfExpensesInUSD(mileagePerLiter,
                                                             priceOfOneLiterInSRD,
                                                             hourlyNetIncomeInSRD)
                        +
                (weightFactor.calculateWeightOfLeftOverMoneyAfterExchangeAndExpensesInUSD(mileagePerLiter,
                                                            priceOfOneLiterInSRD,
                                                            hourlyNetIncomeInSRD,
                                                            moneyToExchangeFromSRDToUSD)))
                +
                " - the total expenses USD " + (weightFactor.calculateWeightOfExpensesInUSD(mileagePerLiter,
                                                                                            priceOfOneLiterInSRD,
                                                                                            hourlyNetIncomeInSRD))
                +
                ") = USD " + weightFactor.calculateWeightOfLeftOverMoneyAfterExchangeAndExpensesInUSD(mileagePerLiter,
                                                                                            priceOfOneLiterInSRD,
                                                                                            hourlyNetIncomeInSRD,
                                                                                            moneyToExchangeFromSRDToUSD));

        System.out.print("\n Weight from " + vertexList.get(start).getLocatie() + " -> " + vertexList.get(end).getLocatie() + " = " + adjMat[start][end]);

        System.out.println();

    }
// -------------------------------------------------------------

    // -------------------------------------------------------------
    public void displayVertex(int vertexIndex) {
        System.out.print(vertexList.get(vertexIndex).getLocatie());
    }

    // -------------------------------------------------------------
    public void mstw(int startingIndex)           // minimum spanning tree
    {
        currentVert = startingIndex;          // start at 0

        while (nTree < nVerts - 1)   // while not all verts in tree
        {                      // put currentVert in tree
            vertexList.get(currentVert).setInTree(true);
            nTree++;

            // insert edges adjacent to currentVert into PQ
            for (int vertexIndex = 0; vertexIndex < nVerts; vertexIndex++)   // for each vertex,
            {
                if (vertexIndex == currentVert)         // skip if it's us
                {
                    continue;

                }
                if (vertexList.get(vertexIndex).isInTree()) // skip if in the tree
                {
                    continue;

                }
                double distance = adjMat[currentVert][vertexIndex];
                if (distance == INFINITY)  // skip if no edge
                {
                    continue;

                }
                putInPQ(vertexIndex, distance);      // put it in PQ (maybe)

            }
            if (thePQ.size() == 0)           // no vertices in PQ?
            {
                System.out.println(" GRAPH NOT CONNECTED");
                return;
            }
            // remove edge with minimum distance, from PQ
            Edge theEdge = thePQ.removeMin();
            int sourceVert = theEdge.srcVert;
            currentVert = theEdge.destVert;

            // display edge from source to current
            System.out.print("Route from " + vertexList.get(sourceVert).getLocatie() + " to ");
            System.out.println(vertexList.get(currentVert).getLocatie() + "( USD " + adjMat[sourceVert][currentVert] +" left)");

            total += adjMat[sourceVert][currentVert];


        }  // end while(not all verts in tree)

        // mst is complete
        for (int vertexIndex = 0; vertexIndex < nVerts; vertexIndex++)     // unmark vertices
            vertexList.get(vertexIndex).setInTree(false);
    }  // end mstw

    // -------------------------------------------------------------
    public void putInPQ(int newVert, double newDist) {
        // is there another edge with the same destination vertex?
        int queueIndex = thePQ.find(newVert);
        if (queueIndex != -1)              // got edge's index
        {
            Edge tempEdge = thePQ.peekN(queueIndex);  // get edge
            double oldDist = tempEdge.distance;
            if (oldDist > newDist)          // if new edge shorter,
            {
                thePQ.removeN(queueIndex);  // remove old edge
                Edge theEdge =
                        new Edge(currentVert, newVert, newDist);
                thePQ.insert(theEdge);      // insert new edge
            }
            // else no action; just leave the old vertex there
        }  // end if
        else  // no edge with same destination vertex
        {                              // so insert new one
            Edge theEdge = new Edge(currentVert, newVert, newDist);
            thePQ.insert(theEdge);
        }
    }  // end putInPQ()

    public void getTotal() {
        System.out.println(total);
    }

    @Override
    public double[][] getEdges() {
        return this.adjMat;
    }

    @Override
    public void setEdges(double[][] adjMat) {

        this.adjMat = adjMat;

    }

    @Override
    public void addVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }
}  // end class Graph
