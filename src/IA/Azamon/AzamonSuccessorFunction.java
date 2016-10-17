package IA.Azamon;
import java.util.ArrayList;
import java.util.List;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class AzamonSuccessorFunction implements SuccessorFunction {
	
	public List getSuccessors(Object aState) {
		ArrayList<AzamonEstado> retVal = new ArrayList();
                AzamonEstado estat = (AzamonEstado) aState;
                for(int i = 0; i < estat.paq.size(); ++i){
                    for(int j = 0; j < estat.trans.size(); ++j){
                        AzamonEstado nouEstat = new AzamonEstado(estat);
                        if(nouEstat.moverPaquete(i, j)){
                            retVal.add(nouEstat);
                        }
                    }
                    for(int j = i +1; j < estat.paq.size() -1; ++j){
                        AzamonEstado nouEstat = new AzamonEstado(estat);
                        if(nouEstat.permutarPaquetes(i, j)){
                            retVal.add(nouEstat);
                        }
                    }
                }
		return retVal;
	}

}
