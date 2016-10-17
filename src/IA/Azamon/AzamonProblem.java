package IA.Azamon;

import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;

public class AzamonProblem {
	
	public static void main(String[] args) {
		AzamonEstado estat = new AzamonEstado(100, 1234, 1.2, 1234);
		//AzamonHillClimbingSearch(estat);
		//AzamonSimulatedAnnealingSearch();
	}
	
	private static void AzamonHillClimbingSearch(AzamonEstado estat) {
		try {
			Problem problem = new Problem(estat, new AzamonSuccessorFunction(), new AzamonGoalTest(), new AzamonHeuristicFunction1());
			Search search = new HillClimbingSearch();
			SearchAgent agent = new SearchAgent(problem, search);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
