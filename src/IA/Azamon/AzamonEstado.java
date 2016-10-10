package IA.Azamon;
import java.util.*;


public class AzamonEstado {

	//Atributos
	static Paquetes paq;
	static Transporte trans;
	private ArrayList<Integer> asignacion;
	private ArrayList<Double> capActual; //capacidad actual de cada oferta
	
	public AzamonEstado(int npaq, int seedpaq, double proporcion, int seedtrans) {
		asignacion = new ArrayList<Integer>(npaq);
		for (int i = 0; i < npaq; i++) {
			asignacion.add(-1);
		}
		paq = new Paquetes(npaq, seedpaq);
		trans = new Transporte(paq, proporcion, seedtrans);
		capActual = new ArrayList<Double>(trans.size());
		for (int i = 0; i < trans.size(); i++) {
			capActual.add(0.0);
		}
	}

	//Métodos
	
	public boolean isGoalState() {
		return false;
	}
	
	
	
	
	public void generaSolInicial1() {
		Collections.sort(paq, new Comparator<Paquete>() {
		    @Override
		    public int compare(Paquete p1, Paquete p2) {
		        return ((Integer)p1.getPrioridad()).compareTo((Integer)p2.getPrioridad());
		    }
		});
		ArrayList<Boolean> asignados = new ArrayList<Boolean>(paq.size());
		int npaq = 0;
		for (int j = 0; j < 3; j++) {
			for(int i = 0; i < trans.size(); i++) {
				if (j == 0 && trans.get(i).getDias() == 1) {
						while (capActual.get(i) > trans.get(i).getPesomax()) {
							if(!asignados.get(npaq)) {
								asignacion.set(npaq, i);
								asignados.set(npaq, false);
							}
						}
				}
				
				
			}
		}
	}

	public void moverPaquete (int p, int o) {
		asignacion.set(p, o);
		//p -> id paquete en Paquetes, o -> id oferta en Transporte
		
	}
	
	public void permutarPaquetes (int p1, int p2) {
		int o;
		o = asignacion.get(p1);
		asignacion.set(p1, asignacion.get(p2));
		asignacion.set(p2, o);
		//p1/p2 -> id paquete p1/p2 en Paquetes
		
	}
	
	public void anadirPaquete (int p, int o) {
		asignacion.set(p, o);
	}
	
	public void quitarPaquete (int p) {
		asignacion.set(p, -1);
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
