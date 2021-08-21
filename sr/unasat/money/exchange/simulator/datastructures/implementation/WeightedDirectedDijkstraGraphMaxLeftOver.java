package sr.unasat.money.exchange.simulator.datastructures.implementation;

import sr.unasat.money.exchange.simulator.datastructures.adt.DijkstraGraph;
import sr.unasat.money.exchange.simulator.datastructures.adt.List;
import sr.unasat.money.exchange.simulator.entities.*;

public class WeightedDirectedDijkstraGraphMaxLeftOver implements DijkstraGraph {

    private final int MAX_VERTS = 20;
    private final int INFINITY = 1000000;
    //    private Vertex vertexList[]; // list of vertices
    private List<Vertex> vertexList;
    private double adjMat[][];      // adjacency matrix
    private int nVerts;          // current number of vertices
    private int nTree;           // number of verts in tree
    private DistPar sPath[];     // array for shortest-path data
    private int currentVert;     // current vertex
    private double startToCurrent;  // distance to currentVert
    private double priceOfOneLiterInSRD;
    private double mileagePerLiter;
    private double moneyToExchangeFromSRDToUSD;
    private double hourlyNetIncomeInSRD;


    public WeightedDirectedDijkstraGraphMaxLeftOver(double priceOfOneLiterInSRD,
                                            double moneyToExchangeFromUSDToSRD,
                                            double hourlyNetIncomeInSRD,
                                            double mileagePerLiter
    ) {

        this.priceOfOneLiterInSRD = priceOfOneLiterInSRD;
        this.moneyToExchangeFromSRDToUSD = moneyToExchangeFromUSDToSRD;
        this.hourlyNetIncomeInSRD = hourlyNetIncomeInSRD;
        this.mileagePerLiter = mileagePerLiter;

//        vertexList = new Vertex[MAX_VERTS];
        vertexList = new ArrayList<>(MAX_VERTS);
        // adjacency matrix
        adjMat = new double[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        nTree = 0;
        for (int rowIndex = 0; rowIndex < MAX_VERTS; rowIndex++)     // set adjacency
        {
            for (int columnIndex = 0; columnIndex < MAX_VERTS; columnIndex++)  //     matrix
                adjMat[rowIndex][columnIndex] = INFINITY;//     to infinity
        }
        sPath = new DistPar[MAX_VERTS];    // shortest paths
    }

    @Override
    public void addVertex(Vertex vertex) {
        nVerts++;
        vertexList.add(vertex);
    }

    @Override
    public void addEdge(int start, int end, Weight weightFactor) {
        adjMat[start][end] = weightFactor.calculateWeightOfLeftOverMoneyAfterExchangeAndExpensesInUSD(mileagePerLiter,
                priceOfOneLiterInSRD,
                hourlyNetIncomeInSRD,
                moneyToExchangeFromSRDToUSD) * -1; // (directed)

        System.out.println("Weight :" + vertexList.get(start).getLocatie() + " -> " + vertexList.get(end).getLocatie() + " :" + weightFactor.calculateWeightOfLeftOverMoneyAfterExchangeAndExpensesInUSD(mileagePerLiter,
                priceOfOneLiterInSRD,
                hourlyNetIncomeInSRD,
                moneyToExchangeFromSRDToUSD) );
    }

    @Override
    public void path(int startTree) {
                     // start at vertex
        vertexList.get(startTree).setInTree(true);
        nTree = 1;                     // put it in tree

        // transfer row of distances from adjMat to sPath
        for (int columnIndex = 0; columnIndex < nVerts; columnIndex++) {
            double tempDist = adjMat[startTree][columnIndex];
            sPath[columnIndex] = new DistPar(startTree, tempDist);
        }

        // until all vertices are in the tree
        while (nTree < nVerts) {
            int indexMin = getMin();    // get minimum from sPath
            double minDist = sPath[indexMin].getDistance();

            if (minDist == INFINITY)     // if all infinite
            {                        // or in tree,
                System.out.println("There are unreachable vertices");
                break;                   // sPath is complete
            } else {                        // reset currentVert
                currentVert = indexMin;  // to closest vert
                startToCurrent = sPath[indexMin].getDistance();
                // minimum distance from startTree is
                // to currentVert, and is startToCurrent
            }
            // put current vertex in tree
            vertexList.get(currentVert).setInTree(true);
            nTree++;
            adjust_sPath();             // update sPath[] array
        }  // end while(nTree<nVerts)

        displayPaths();                // display sPath[] contents

        nTree = 0;                     // clear tree
        for (int vertexIndex = 0; vertexIndex < nVerts; vertexIndex++)
            vertexList.get(vertexIndex).setInTree(false);
        ;
    }  // end path()
    // -------------------------------------------------------------


    private int getMin()               // get entry from sPath
    {                              //    with minimum distance
        double minDist = INFINITY;        // assume minimum
        int indexMin = 0;
        for (int vertexIndex = 1; vertexIndex < nVerts; vertexIndex++)    // for each vertex,
        {                           // if it's in tree and
            if (!vertexList.get(vertexIndex).isInTree() &&  // smaller than old one
                    sPath[vertexIndex].getDistance()  < minDist) {
                minDist = sPath[vertexIndex].getDistance();
                indexMin = vertexIndex;            // update minimum
            }
        }  // end for
        return indexMin;               // return index of minimum
    }  // end getMin()
    // -------------------------------------------------------------


    private void adjust_sPath() {
        // adjust values in shortest-path array sPath
        int column = 1;                // skip starting vertex
        while (column < nVerts)         // go across columns
        {
            // if this column's vertex already in tree, skip it
            if (vertexList.get(column).isInTree()) {
                column++;
                continue;
            }
            // calculate distance for one sPath entry
            // get edge from currentVert to column
            double currentToFringe = adjMat[currentVert][column];
            // add distance from start
            double startToFringe = startToCurrent + currentToFringe;
            // get distance of current sPath entry
            double sPathDist = sPath[column].getDistance();

            // compare distance from start with sPath entry
            if (startToFringe < sPathDist)   // if shorter,
            {                            // update sPath
                sPath[column].setParentVert(currentVert);
                sPath[column].setDistance(startToFringe);
            }
            column++;
        }  // end while(column < nVerts)
    }  // end adjust_sPath()
    // -------------------------------------------------------------

    @Override
    public void displayPaths() {

        System.out.println("");

        for (int vertexIndex = 0; vertexIndex < nVerts; vertexIndex++) // display contents of sPath[]
        {
            System.out.print(vertexList.get(vertexIndex).getLocatie() + "=");
            if (sPath[vertexIndex].getDistance() == INFINITY)
            {
                System.out.print("inf");                  // inf

            }
            else {
                System.out.print(" USD "+-sPath[vertexIndex].getDistance());
            }
            Locatie parent = vertexList.get(sPath[vertexIndex].getParentVert()).getLocatie();
            System.out.println(" ( via " + parent + ") ");
        }
    }

    public static void main(String[] args) {
        DijkstraGraph dijkstraGraph = new WeightedDirectedDijkstraGraphMaxLeftOver(1,
                100000,
                1,
                1);


        dijkstraGraph.addVertex(new Vertex(new Locatie("Nieuw nickerie "))); //0
        dijkstraGraph.addVertex(new Vertex(new Locatie("Coppename punt "))); //1
        dijkstraGraph.addVertex(new Vertex(new Locatie("Pomona "))); //2
        dijkstraGraph.addVertex(new Vertex(new Locatie("Leiding 9A "))); //3
        dijkstraGraph.addVertex(new Vertex(new Locatie("Kasabaholo "))); //4
        dijkstraGraph.addVertex(new Vertex(new Locatie("Garnizoenspad "))); //5
        dijkstraGraph.addVertex(new Vertex(new Locatie("Kwarasan "))); //6
        dijkstraGraph.addVertex(new Vertex(new Locatie("Watermolenstraat "))); //7

        dijkstraGraph.addEdge(0, 1, new Weight(1, 1, (new Cambio(100, "Cambio nick-copp")))); //nick -> copp
        dijkstraGraph.addEdge(0, 2, new Weight(1, 1, (new Cambio(200, "Cambio nick-pomona")))); // nick -> pomona
        dijkstraGraph.addEdge(1, 2, new Weight(1, 1, (new Cambio(300, "Cambio copp-pomona")))); // copp ->pomona
        dijkstraGraph.addEdge(1, 5, new Weight(1, 1, (new Cambio(400, "Cambio copp-garnizoenspad")))); // copp -> garnizoenspad
        dijkstraGraph.addEdge(2, 5, new Weight(1, 1, (new Cambio(500, "Cambio pomona-garnizoenspad")))); // pomona -> garnizoenspad
        dijkstraGraph.addEdge(2, 4, new Weight(1, 1, (new Cambio(600, "Cambio pomona-kasabaholo")))); // pomona -> kasabaholo
        dijkstraGraph.addEdge(2, 3, new Weight(1, 1, (new Cambio(700, "Cambio pomona-leiding 9A")))); // pomona -> leiding 9A
        dijkstraGraph.addEdge(3, 6, new Weight(1, 1, (new Cambio(800, "Cambio leiding 9A-kwarasan")))); // leiding 9 A -> kwarasan
        dijkstraGraph.addEdge(4, 1, new Weight(1, 1, (new Cambio(900, "Cambio kasabaholo-copp")))); // kasabaholo -> copp
        dijkstraGraph.addEdge(4, 3, new Weight(1, 1, (new Cambio(1000, "Cambio kasabaholo-leiding 9A")))); // kasabaholo -> leiding 9A
        dijkstraGraph.addEdge(4, 6, new Weight(1, 1, (new Cambio(1100, "Cambio kasabaholo-kwarasan")))); // kasabaholo -> kwarasan
        dijkstraGraph.addEdge(4, 7, new Weight(1, 1, (new Cambio(1200, "Cambio kasabaholo-watermolenstraat")))); // kasabaholo -> watermolenstraat
        dijkstraGraph.addEdge(5, 7, new Weight(1, 1, (new Cambio(1300, "Cambio garnizoenspad-watermolenstraat")))); // garnizoenspad -> watermolenstraat
        dijkstraGraph.addEdge(5, 4, new Weight(1, 1, (new Cambio(1400, "Cambio garnizoenspad-kasabaholo")))); // garnizoenspad -> kasabaholo
        dijkstraGraph.addEdge(7, 6, new Weight(1, 1, (new Cambio(1500, "Cambio watermolenstraat-kwarasan")))); // watermolenstraat -> kwarasan


        dijkstraGraph.path(0);
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
}
