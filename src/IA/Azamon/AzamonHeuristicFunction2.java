package IA.Azamon;

import java.util.ArrayList;
import java.util.Collections;

import aima.search.framework.HeuristicFunction;


// Aquest valora els costos-felicitat.
public class AzamonHeuristicFunction2 implements HeuristicFunction {
	private ArrayList<Double> pes_restant;
	private ArrayList<Double> paq_prior2;
	ArrayList<Pair> paq_dies = new ArrayList<Pair>();  
	public double getHeuristicValue(Object state) {
			AzamonEstado estat = (AzamonEstado) state;
			pes_restant = estat.kg_restants();
			//pes_restant => pes restant de totes les ofertes ordenades de 1..n
			//ara volem saber per un paquet, en quants dies s'entregara
			paq_dies = estat.paq_dies();
			//paq_dies => la primera posicio es el nº de paquet, la segona posicio es els dies que s'entrega
			//ara ordenem per quants dies d'entrega, de menor a major
			Collections.sort(paq_dies);
			//paq_dies ordenat començant per el menor dia d'entrega
			double preu_actual, pes_paquet = 0;
			int paquet = 0;
			for(int j = 2; j < 6; ++j) {
				for(int i = 1; i < paq_dies.size(); ++i) {
					if(paq_dies.get(i).getY() == j) { //si els dies que triga l'entrega son j agafem el paquet
						paquet = paq_dies.get(i).getX(); //obtenim el paquet
						pes_paquet = estat.pes_paquet(paquet); //obtenim el pes del paquet
						preu_actual = pes_paquet * estat.preu_oferta_paquet(paquet);
						if(j == 3 || j == 4) preu_actual = preu_actual + pes_paquet * 0.25;
						else if(j == 5) preu_actual = preu_actual + pes_paquet * 0.5;
						//ja tenim el preu total que ens costa en l'oferta ACTUAL
						double pespreu_nova = 0;
						int k = (estat.comparar_ofertes(preu_actual, pes_paquet, j));
						boolean a = false; //per quan trobi una oferta que costi menys que l'actual
						if (k != 0) estat.modificar_capac(paquet, pes_paquet, k);	
					}	
				}
			} //HA DE RETORNAR DOUBLE LOOOL
	}		
}
	

