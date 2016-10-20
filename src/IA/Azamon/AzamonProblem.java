package IA.Azamon;

import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import java.util.List;
import java.util.Iterator;
import java.util.Properties;

public class AzamonProblem {
	
	public static void main(String[] args) {
		AzamonEstado estat = new AzamonEstado(500, 12387, 1.2, 1234);
		estat.generaSolInicial1();
		System.out.print(estat.toString());
		System.out.print(estat.correspondenciasToString());
		AzamonHillClimbingSearch(estat);
		//AzamonSimulatedAnnealingSearch();
	}
	
	private static void AzamonHillClimbingSearch(AzamonEstado estat) {
		try {
			Problem problem = new Problem(estat, new AzamonSuccessorFunction(), new AzamonGoalTest(), new AzamonHeuristicFunction1());
			Search search = new HillClimbingSearch();
			SearchAgent agent = new SearchAgent(problem, search);
                        
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
            System.out.println("\n"+((AzamonEstado)search.getGoalState()).toString());
            System.out.println("\n"+((AzamonEstado)search.getGoalState()).correspondenciasToString());
		}
		catch (Exception e) {
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
