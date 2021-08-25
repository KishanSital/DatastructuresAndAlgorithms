package sr.unasat.money.exchange.simulator.app;


import sr.unasat.money.exchange.simulator.datastructures.adt.*;
import sr.unasat.money.exchange.simulator.datastructures.implementation.*;
import sr.unasat.money.exchange.simulator.entities.Cambio;
import sr.unasat.money.exchange.simulator.entities.Locatie;
import sr.unasat.money.exchange.simulator.entities.Vertex;
import sr.unasat.money.exchange.simulator.entities.Weight;

public class app {
    public static void main(String[] args) {

        List<Vertex> vertexList = new ArrayList<>();
        vertexList.add(new Vertex(new Locatie("Nieuw nickerie "))); //0
        vertexList.add(new Vertex(new Locatie("Coppename punt ")));//1
        vertexList.add(new Vertex(new Locatie("Pomona ")));//2
        vertexList.add(new Vertex(new Locatie("Leiding 9A ")));//3
        vertexList.add(new Vertex(new Locatie("Kasabaholo ")));//4
        vertexList.add(new Vertex(new Locatie("Garnizoenspad ")));//5
        vertexList.add(new Vertex(new Locatie("Kwarasan ")));//6
        vertexList.add(new Vertex(new Locatie("Watermolenstraat ")));//7


        int startingFromNiewNickerie = 0;
        int destinationIsKasabaholo = 4;

        DfsGraph dfsGraph = new DfsGraphImpl();
        dfsGraph.addVertexList(vertexList);

        dfsGraph.addEdge(0, 1); //nick -> copp
        dfsGraph.addEdge(0, 2); // nick -> pomona
        dfsGraph.addEdge(1, 2); // copp ->pomona
        dfsGraph.addEdge(1, 5); // copp -> garnizoenspad
        dfsGraph.addEdge(2, 5); // pomona -> garnizoenspad
        dfsGraph.addEdge(2, 4); // pomona -> kasabaholo
        dfsGraph.addEdge(2, 3); // pomona -> leiding 9A
        dfsGraph.addEdge(3, 6); // leiding 9 A -> kwarasan
        dfsGraph.addEdge(4, 1); // kasabaholo -> copp
        dfsGraph.addEdge(4, 3); // kasabaholo -> leiding 9A
        dfsGraph.addEdge(4, 5); //  kasabaholo -> garnizoenspad
        dfsGraph.addEdge(4, 6); // kasabaholo -> kwarasan
        dfsGraph.addEdge(4, 7); // kasabaholo -> watermolenstraat
        dfsGraph.addEdge(5, 7); // garnizoenspad -> watermolenstraat
        dfsGraph.addEdge(7, 6); // watermolenstraat -> kwarasan*/



        System.out.println();
        System.out.println("Depth first search to : " + vertexList.get(destinationIsKasabaholo).getLocatie());

        dfsGraph.dfs(startingFromNiewNickerie, destinationIsKasabaholo);             // depth-first search

        System.out.println("\n\n");

        System.out.println("----------------------------------------------------------------------------------------------------------");

        BfsGraph bfsGraph = new BfsGraphImpl();
        bfsGraph.addVertexList(vertexList);

        bfsGraph.addEdge(0, 1); //nick -> copp
        bfsGraph.addEdge(0, 2); // nick -> pomona
        bfsGraph.addEdge(1, 2); // copp ->pomona
        bfsGraph.addEdge(1, 5); // copp -> garnizoenspad
        bfsGraph.addEdge(2, 5); // pomona -> garnizoenspad
        bfsGraph.addEdge(2, 4); // pomona -> kasabaholo
        bfsGraph.addEdge(2, 3); // pomona -> leiding 9A
        bfsGraph.addEdge(3, 6); // leiding 9 A -> kwarasan
        bfsGraph.addEdge(4, 1); // kasabaholo -> copp
        bfsGraph.addEdge(4, 3); // kasabaholo -> leiding 9A
        bfsGraph.addEdge(4, 5); //  kasabaholo -> garnizoenspad
        bfsGraph.addEdge(4, 6); // kasabaholo -> kwarasan
        bfsGraph.addEdge(4, 7); // kasabaholo -> watermolenstraat
        bfsGraph.addEdge(5, 7); // garnizoenspad -> watermolenstraat
        bfsGraph.addEdge(7, 6); // watermolenstraat -> kwarasan*/

        System.out.println("Breadth first search\n");

        bfsGraph.bfs(startingFromNiewNickerie);

        System.out.println("\n\n");

        System.out.println("----------------------------------------------------------------------------------------------------------");

        MstwGraph mstwGraph = new MstwGraphMinExpenses(1,
                1000,
                10);

        System.out.println("Minimum spanning tree (weighted) based on just the expenses (time and distance)\n\nThese are the expenses:");

        mstwGraph.addVertex(new Vertex(new Locatie("Nieuw nickerie ")));
        mstwGraph.addVertex(new Vertex(new Locatie("Coppename punt ")));
        mstwGraph.addVertex(new Vertex(new Locatie("Pomona ")));
        mstwGraph.addVertex(new Vertex(new Locatie("Leiding 9A ")));
        mstwGraph.addVertex(new Vertex(new Locatie("Kasabaholo ")));
        mstwGraph.addVertex(new Vertex(new Locatie("Garnizoenspad ")));
        mstwGraph.addVertex(new Vertex(new Locatie("Kwarasan ")));
        mstwGraph.addVertex(new Vertex(new Locatie("Watermolenstraat ")));

        mstwGraph.addEdge(0, 1, new Weight(1, 1, (new Cambio(100, "Cambio nick-copp")))); //nick -> copp
        mstwGraph.addEdge(0, 2, new Weight(1, 1, (new Cambio(200, "Cambio nick-pomona")))); // nick -> pomona
        mstwGraph.addEdge(1, 2, new Weight(1, 1, (new Cambio(300, "Cambio copp-pomona")))); // copp ->pomona
        mstwGraph.addEdge(1, 5, new Weight(1, 1, (new Cambio(400, "Cambio copp-garnizoenspad")))); // copp -> garnizoenspad
        mstwGraph.addEdge(2, 5, new Weight(1, 1, (new Cambio(500, "Cambio pomona-garnizoenspad")))); // pomona -> garnizoenspad
        mstwGraph.addEdge(2, 4, new Weight(1, 1, (new Cambio(600, "Cambio pomona-kasabaholo")))); // pomona -> kasabaholo
        mstwGraph.addEdge(2, 3, new Weight(1, 1, (new Cambio(700, "Cambio pomona-leiding 9A")))); // pomona -> leiding 9A
        mstwGraph.addEdge(3, 6, new Weight(1, 1, (new Cambio(800, "Cambio leiding 9A-kwarasan")))); // leiding 9 A -> kwarasan
        mstwGraph.addEdge(4, 1, new Weight(1, 1, (new Cambio(900, "Cambio kasabaholo-copp")))); // kasabaholo -> copp
        mstwGraph.addEdge(4, 3, new Weight(1, 1, (new Cambio(1000, "Cambio kasabaholo-leiding 9A")))); // kasabaholo -> leiding 9A
        mstwGraph.addEdge(4, 5, new Weight(1, 1, (new Cambio(1400, "Cambio kasabaholo-garnizoenspad")))); // garnizoenspad -> kasabaholo
        mstwGraph.addEdge(4, 6, new Weight(1, 1, (new Cambio(1100, "Cambio kasabaholo-kwarasan")))); // kasabaholo -> kwarasan
        mstwGraph.addEdge(4, 7, new Weight(1, 1, (new Cambio(1200, "Cambio kasabaholo-watermolenstraat")))); // kasabaholo -> watermolenstraat
        mstwGraph.addEdge(5, 7, new Weight(1, 1, (new Cambio(1300, "Cambio garnizoenspad-watermolenstraat")))); // garnizoenspad -> watermolenstraat
        mstwGraph.addEdge(7, 6, new Weight(1, 1, (new Cambio(1500, "Cambio watermolenstraat-kwarasan")))); // watermolenstraat -> kwarasan

        System.out.println();
        System.out.println("Routes to take if you'd like to have the least expenses\n");
        mstwGraph.mstw(startingFromNiewNickerie);
        System.out.print("Total weight consists of: ");
        mstwGraph.getTotal();

        System.out.println("\n\n");
        System.out.println("----------------------------------------------------------------------------------------------------------");


        mstwGraph = new MstwGraphMinLeftOver(1,
                10000,
                10,
                1);

        System.out.println("Minimum spanning tree (weighted) based on left over money after taking all expenses into consideration and exchanging money");

        mstwGraph.addVertex(new Vertex(new Locatie("Nieuw nickerie "))); //0
        mstwGraph.addVertex(new Vertex(new Locatie("Coppename punt "))); //1
        mstwGraph.addVertex(new Vertex(new Locatie("Pomona "))); //2
        mstwGraph.addVertex(new Vertex(new Locatie("Leiding 9A "))); //3
        mstwGraph.addVertex(new Vertex(new Locatie("Kasabaholo "))); //4
        mstwGraph.addVertex(new Vertex(new Locatie("Garnizoenspad "))); //5
        mstwGraph.addVertex(new Vertex(new Locatie("Kwarasan "))); //6
        mstwGraph.addVertex(new Vertex(new Locatie("Watermolenstraat "))); //7

        mstwGraph.addEdge(0, 1, new Weight(1, 1, (new Cambio(100, "Cambio nick-copp")))); //nick -> copp
        mstwGraph.addEdge(0, 2, new Weight(1, 1, (new Cambio(200, "Cambio nick-pomona")))); // nick -> pomona
        mstwGraph.addEdge(1, 2, new Weight(1, 1, (new Cambio(300, "Cambio copp-pomona")))); // copp ->pomona
        mstwGraph.addEdge(1, 5, new Weight(1, 1, (new Cambio(400, "Cambio copp-garnizoenspad")))); // copp -> garnizoenspad
        mstwGraph.addEdge(2, 5, new Weight(1, 1, (new Cambio(500, "Cambio pomona-garnizoenspad")))); // pomona -> garnizoenspad
        mstwGraph.addEdge(2, 4, new Weight(1, 1, (new Cambio(600, "Cambio pomona-kasabaholo")))); // pomona -> kasabaholo
        mstwGraph.addEdge(2, 3, new Weight(1, 1, (new Cambio(700, "Cambio pomona-leiding 9A")))); // pomona -> leiding 9A
        mstwGraph.addEdge(3, 6, new Weight(1, 1, (new Cambio(800, "Cambio leiding 9A-kwarasan")))); // leiding 9 A -> kwarasan
        mstwGraph.addEdge(4, 1, new Weight(1, 1, (new Cambio(900, "Cambio kasabaholo-copp")))); // kasabaholo -> copp
        mstwGraph.addEdge(4, 3, new Weight(1, 1, (new Cambio(1000, "Cambio kasabaholo-leiding 9A")))); // kasabaholo -> leiding 9A
        mstwGraph.addEdge(4, 5, new Weight(1, 1, (new Cambio(1400, "Cambio kasabaholo-garnizoenspad")))); // kasabaholo -> garnizoenspad
        mstwGraph.addEdge(4, 6, new Weight(1, 1, (new Cambio(1100, "Cambio kasabaholo-kwarasan")))); // kasabaholo -> kwarasan
        mstwGraph.addEdge(4, 7, new Weight(1, 1, (new Cambio(1200, "Cambio kasabaholo-watermolenstraat")))); // kasabaholo -> watermolenstraat
        mstwGraph.addEdge(5, 7, new Weight(1, 1, (new Cambio(1300, "Cambio garnizoenspad-watermolenstraat")))); // garnizoenspad -> watermolenstraat
        mstwGraph.addEdge(7, 6, new Weight(1, 1, (new Cambio(1500, "Cambio watermolenstraat-kwarasan")))); // watermolenstraat -> kwarasan

        System.out.println();
        System.out.println("Routes to take if you'd like to have the least money left over after exchanging money and taking the expenses into consideration\n");
        mstwGraph.mstw(startingFromNiewNickerie);
        System.out.print("Total weight consists of: ");
        mstwGraph.getTotal();

        System.out.println("\n\n");
        System.out.println("----------------------------------------------------------------------------------------------------------");


        DijkstraGraph dijkstraGraph = new WeightedDirectedDijkstraGraphMaxExpenses(1,
                100000,
                1);


        dijkstraGraph.addVertex(new Vertex(new Locatie("Nieuw nickerie "))); //0
        dijkstraGraph.addVertex(new Vertex(new Locatie("Coppename punt "))); //1
        dijkstraGraph.addVertex(new Vertex(new Locatie("Pomona "))); //2
        dijkstraGraph.addVertex(new Vertex(new Locatie("Leiding 9A "))); //3
        dijkstraGraph.addVertex(new Vertex(new Locatie("Kasabaholo "))); //4
        dijkstraGraph.addVertex(new Vertex(new Locatie("Garnizoenspad "))); //5
        dijkstraGraph.addVertex(new Vertex(new Locatie("Kwarasan "))); //6
        dijkstraGraph.addVertex(new Vertex(new Locatie("Watermolenstraat "))); //7

        System.out.println("Dijkstra's algorithm to display the routes where the expenses are the highest\n");

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
        dijkstraGraph.addEdge(4, 5, new Weight(1, 1, (new Cambio(1400, "Cambio kasabaholo-garnizoenspad")))); // kasabaholo -> garnizoenspad
        dijkstraGraph.addEdge(4, 6, new Weight(1, 1, (new Cambio(1100, "Cambio kasabaholo-kwarasan")))); // kasabaholo -> kwarasan
        dijkstraGraph.addEdge(4, 7, new Weight(1, 1, (new Cambio(1200, "Cambio kasabaholo-watermolenstraat")))); // kasabaholo -> watermolenstraat
        dijkstraGraph.addEdge(5, 7, new Weight(1, 1, (new Cambio(1300, "Cambio garnizoenspad-watermolenstraat")))); // garnizoenspad -> watermolenstraat
        dijkstraGraph.addEdge(7, 6, new Weight(1, 1, (new Cambio(1500, "Cambio watermolenstraat-kwarasan")))); // watermolenstraat -> kwarasan

        System.out.println();
        System.out.println("Displaying the paths where the expenses are at their highest to travel from location to location\n");

        dijkstraGraph.path(startingFromNiewNickerie);

        System.out.println("\n\n");
        System.out.println("----------------------------------------------------------------------------------------------------------");

        dijkstraGraph = new WeightedDirectedDijkstraGraphMaxLeftOver(1,
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

        System.out.println("Dijkstra's algorithm to display the routes where the left over money after subtracting expenses from it will be the highest\n");


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
        dijkstraGraph.addEdge(4, 5, new Weight(1, 1, (new Cambio(1400, "Cambio kasabaholo-garnizoenspad")))); // kasabaholo -> garnizoenspad
        dijkstraGraph.addEdge(4, 6, new Weight(1, 1, (new Cambio(1100, "Cambio kasabaholo-kwarasan")))); // kasabaholo -> kwarasan
        dijkstraGraph.addEdge(4, 7, new Weight(1, 1, (new Cambio(1200, "Cambio kasabaholo-watermolenstraat")))); // kasabaholo -> watermolenstraat
        dijkstraGraph.addEdge(5, 7, new Weight(1, 1, (new Cambio(1300, "Cambio garnizoenspad-watermolenstraat")))); // garnizoenspad -> watermolenstraat
        dijkstraGraph.addEdge(7, 6, new Weight(1, 1, (new Cambio(1500, "Cambio watermolenstraat-kwarasan")))); // watermolenstraat -> kwarasan

        System.out.println();
        System.out.println("Displaying the paths where the left over money will be the highest after exchanging and subtracting the expenses from it whilst traveling from location to location");

        dijkstraGraph.path(startingFromNiewNickerie);
        System.out.println("\n\n");
        System.out.println("----------------------------------------------------------------------------------------------------------");

        dijkstraGraph = new WeightedDirectedDijkstraGraphMinExpenses(1,
                1000,
                10);

        dijkstraGraph.addVertex(new Vertex(new Locatie("Nieuw nickerie "))); //0
        dijkstraGraph.addVertex(new Vertex(new Locatie("Coppename punt "))); //1
        dijkstraGraph.addVertex(new Vertex(new Locatie("Pomona "))); //2
        dijkstraGraph.addVertex(new Vertex(new Locatie("Leiding 9A "))); //3
        dijkstraGraph.addVertex(new Vertex(new Locatie("Kasabaholo "))); //4
        dijkstraGraph.addVertex(new Vertex(new Locatie("Garnizoenspad "))); //5
        dijkstraGraph.addVertex(new Vertex(new Locatie("Kwarasan "))); //6
        dijkstraGraph.addVertex(new Vertex(new Locatie("Watermolenstraat "))); //7


        System.out.println("Dijkstra's algorithm to display the routes where the expenses will be the least\n");


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
        dijkstraGraph.addEdge(4, 5, new Weight(1, 1, (new Cambio(1400, "Cambio kasabaholo-garnizoenspad")))); // kasabaholo -> garnizoenspad
        dijkstraGraph.addEdge(4, 6, new Weight(1, 1, (new Cambio(1100, "Cambio kasabaholo-kwarasan")))); // kasabaholo -> kwarasan
        dijkstraGraph.addEdge(4, 7, new Weight(1, 1, (new Cambio(1200, "Cambio kasabaholo-watermolenstraat")))); // kasabaholo -> watermolenstraat
        dijkstraGraph.addEdge(5, 7, new Weight(1, 1, (new Cambio(1300, "Cambio garnizoenspad-watermolenstraat")))); // garnizoenspad -> watermolenstraat
        dijkstraGraph.addEdge(7, 6, new Weight(1, 1, (new Cambio(1500, "Cambio watermolenstraat-kwarasan")))); // watermolenstraat -> kwarasan

        System.out.println();
        System.out.println("Displaying the paths where the expenses will be the least whilst traveling from location to location");

        dijkstraGraph.path(startingFromNiewNickerie);
        System.out.println("\n\n");

        System.out.println("----------------------------------------------------------------------------------------------------------");


        dijkstraGraph = new WeightedDirectedDijkstraGraphMinLeftOver(1,
                1000,
                10,
                1);

        dijkstraGraph.addVertex(new Vertex(new Locatie("Nieuw nickerie "))); //0
        dijkstraGraph.addVertex(new Vertex(new Locatie("Coppename punt "))); //1
        dijkstraGraph.addVertex(new Vertex(new Locatie("Pomona "))); //2
        dijkstraGraph.addVertex(new Vertex(new Locatie("Leiding 9A "))); //3
        dijkstraGraph.addVertex(new Vertex(new Locatie("Kasabaholo "))); //4
        dijkstraGraph.addVertex(new Vertex(new Locatie("Garnizoenspad "))); //5
        dijkstraGraph.addVertex(new Vertex(new Locatie("Kwarasan "))); //6
        dijkstraGraph.addVertex(new Vertex(new Locatie("Watermolenstraat "))); //7


        System.out.println("Dijkstra's algorithm to display the routes where the money left over will be the least after taking the expenses in consideration\n");


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
        dijkstraGraph.addEdge(4, 5, new Weight(1, 1, (new Cambio(1400, "Cambio kasabaholo-garnizoenspad")))); // kasabaholo -> garnizoenspad
        dijkstraGraph.addEdge(4, 6, new Weight(1, 1, (new Cambio(1100, "Cambio kasabaholo-kwarasan")))); // kasabaholo -> kwarasan
        dijkstraGraph.addEdge(4, 7, new Weight(1, 1, (new Cambio(1200, "Cambio kasabaholo-watermolenstraat")))); // kasabaholo -> watermolenstraat
        dijkstraGraph.addEdge(5, 7, new Weight(1, 1, (new Cambio(1300, "Cambio garnizoenspad-watermolenstraat")))); // garnizoenspad -> watermolenstraat
        dijkstraGraph.addEdge(7, 6, new Weight(1, 1, (new Cambio(1500, "Cambio watermolenstraat-kwarasan")))); // watermolenstraat -> kwarasan

        System.out.println();

        System.out.println("Displaying the paths where the left over money will be the least after subtracting the expenses from it whilst traveling from location to location");


        dijkstraGraph.path(startingFromNiewNickerie);

    }
}
