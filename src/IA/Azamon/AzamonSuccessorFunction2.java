package IA.Azamon;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;

public class AzamonSuccessorFunction2 {
	
	public List getSuccessors(Object aState) {
		ArrayList<Successor> retVal = new ArrayList<>();
		AzamonEstado estat = (AzamonEstado) aState;
	
		for(int i = 0; i < estat.paq.size(); ++i){
			AzamonEstado nouEstat = new AzamonEstado(estat);
			int of = estat.getOfertaPaquete(i);
			nouEstat.quitarPaquete(i);
			for (int j = 0; j < estat.trans.size(); ++j) {
				if (of != j && nouEstat.anadirPaquete(i, j)) {
					StringBuffer S = new StringBuffer();
					S.append("quitado paquete de oferta "+of+ " y puesto en oferta "+j+"\n");
					retVal.add(new Successor(S.toString(), nouEstat));
				}
			}
		}
		
		return retVal;
	}
}
