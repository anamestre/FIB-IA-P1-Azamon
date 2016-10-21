package IA.Azamon;

import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import java.util.List;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

public class AzamonProblem {

    public static void main(String[] args) {
    	Random myRandom = new Random();
    	//Integer seed = myRandom.nextInt(10000);
    	Integer seed = new Integer(1234);
    	long t0, t1;
        AzamonEstado estat = new AzamonEstado(100, seed, 1.2, seed);
        t0 = System.nanoTime();
        estat.generaSolInicial2();
        //System.out.print(estat.toString());
        //System.out.print(estat.correspondenciasToString());
        AzamonHillClimbingSearch(estat);
        t1 = System.nanoTime();
        t0 = t1 - t0;
        System.out.print("Precio inicial: "+estat.getPrecio()+"\n");
        System.out.print("Tiempo: "+t0+"\n");
        System.out.print("Seed: "+seed+"\n");
        
        //AzamonSimulatedAnnealingSearch(estat);
    }

    private static void AzamonHillClimbingSearch(AzamonEstado estat) {
        try {
            Problem problem = new Problem(estat, new AzamonSuccessorFunction2(), new AzamonGoalTest(), new AzamonHeuristicFunction1());
            Search search = new HillClimbingSearch();
            SearchAgent agent = new SearchAgent(problem, search);

            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
            System.out.println("\n" + ((AzamonEstado) search.getGoalState()).toString());
            //System.out.println("\n" + ((AzamonEstado) search.getGoalState()).correspondenciasToString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void AzamonSimulatedAnnealingSearch(AzamonEstado estat) {
        try {
            Problem problem = new Problem(estat, new AzamonSuccessorFunction(), new AzamonGoalTest(), new AzamonHeuristicFunction1());
            Search search = new SimulatedAnnealingSearch(2000, 100, 5, 0.001);
            SearchAgent agent = new SearchAgent(problem, search);

            System.out.println("\n" + ((AzamonEstado) search.getGoalState()).toString());
            System.out.println("\n" + ((AzamonEstado) search.getGoalState()).correspondenciasToString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            System.out.println(action);
        }
    }

    private static void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }

    }

}
