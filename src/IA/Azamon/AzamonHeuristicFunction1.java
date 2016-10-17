package IA.Azamon;

import aima.search.framework.HeuristicFunction;


// Aquest valora els costos.
public class AzamonHeuristicFunction1 implements HeuristicFunction {
		public double getHeuristicValue(Object state) {
			AzamonEstado estat = (AzamonEstado) state;
                        return estat.getPrecio();
		}
}
