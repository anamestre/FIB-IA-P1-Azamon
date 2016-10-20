package IA.Azamon;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class AzamonSuccessorFunction2 implements SuccessorFunction {
	
	public List getSuccessors(Object aState) {
		ArrayList<Successor> retVal = new ArrayList<>();
		AzamonEstado estat = (AzamonEstado) aState;
		//System.out.print(estat.capActualToString());
		for(int i = 0; i < estat.paq.size(); ++i){
			//System.out.print("It "+i+ " - "+estat.getPrecio()+" - "+estat.capActualToString());
                        for(int j = 0; j < estat.trans.size(); ++j){
				AzamonEstado nouEstat = new AzamonEstado(estat);
				if(nouEstat.moverPaquete(i, j)){
					StringBuffer S = new StringBuffer();
					S.append("movido paquete "+i+ " a oferta "+j+"\n");
					retVal.add(new Successor(S.toString(), nouEstat));
                                }
                        }
		}
		//System.out.print(estat.getPrecio()+" - "+estat.capActualToString());
		return retVal;
	}
}
