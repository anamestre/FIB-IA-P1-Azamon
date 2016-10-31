package IA.Azamon;

import java.util.ArrayList;
import java.util.Collections;

import aima.search.framework.HeuristicFunction;


// Aquest valora els costos-felicitat.
public class AzamonHeuristicFunction2 implements HeuristicFunction {
	
	public double getHeuristicValue(Object state) {
		AzamonEstado estat = (AzamonEstado) state;
                    return estat.getPrecio()-10*(double)estat.getFelicidad();
	}
}
	

