package IA.Azamon;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class AzamonSuccessorFunction2 implements SuccessorFunction {
	
	public List getSuccessors(Object aState) {
		ArrayList<Successor> retVal = new ArrayList<>();
		AzamonEstado estat = (AzamonEstado) aState;
	
		for(int i = 0; i < estat.paq.size(); ++i){
			//System.out.print("Intentant assignar paquet "+i+"\n");
			AzamonEstado nouEstat = new AzamonEstado(estat);
			int of = estat.getOfertaPaquete(i);
			nouEstat.quitarPaquete(i);
			for (int j = 0; j < estat.trans.size(); ++j) {
				//System.out.print("Intentant assignarlo a oferta "+j+"\n");
				if (of != j && nouEstat.anadirPaquete(i, j)) {
					//System.out.print("S'ha assignat a oferta "+ j+ "\n");
					StringBuffer S = new StringBuffer();
					S.append("quitado paquete "+ i+ " de oferta "+of+ " y puesto en oferta "+j+"\n");
					retVal.add(new Successor(S.toString(), nouEstat));
				}
			}
		}
		
		return retVal;
	}
}
