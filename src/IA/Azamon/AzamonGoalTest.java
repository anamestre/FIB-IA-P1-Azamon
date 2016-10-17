package IA.Azamon;
import aima.search.framework.GoalTest;

public class AzamonGoalTest implements GoalTest {
	public boolean isGoalState(Object aState) {
		AzamonEstado estat = (AzamonEstado) aState;
		return (estat.isGoalState());
	}

}
