package IA.Azamon;

import java.util.*;

public class AzamonEstado {
	
	private Map<Integer, Integer> asignacion;
	private ArrayList<Double> capActual;
	
	public AzamonEstado() {
		asignacion = new HashMap<Integer,Integer>();
		capActual = new ArrayList<Double>();
	}
	
	public boolean isGoalState() {
		return false;
	}

	public void moverPaquete (Paquete p, Oferta o) {
		
	}
	
	public void permutarPaquetes (Paquete p1, Paquete p2) {
		
	}
	
	public void anadirPaquete (Paquete p) {
		
	}
	
	public void quitarPaquete (Paquete p) {
		
	}
	
	public int hashFunction() {
		return 0;
	}
	
	public boolean equals(AzamonEstado ae) {
		return false;
	}
	
	public String toString() {
		return "Estado";
	}
	
	public double getCapActualOferta(int i) {
		return capActual.get(i);
	}
	
	public int getOfertaPaquete(int i) {
		return asignacion.get(i);
	}
	
}
