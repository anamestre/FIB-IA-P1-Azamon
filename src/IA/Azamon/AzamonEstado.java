package IA.Azamon;

import java.util.*;

public class AzamonEstado {

    //Atributos
    static Paquetes paq;
    static Transporte trans;
    private ArrayList<Integer> asignacion;
    private ArrayList<Double> capActual; //capacidad actual de cada oferta
    private double precio;
    private int felicidad;

    public int getFelicidad() {
        return felicidad;
    }

    public double getPrecio() {
        return precio;
    }

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
        felicidad = 0;
        precio = 0.0;
    }
    
    public AzamonEstado(AzamonEstado estat){
        paq = estat.paq;
        trans = estat.trans;
        asignacion = new ArrayList<Integer>(estat.asignacion);
        capActual = new ArrayList<Double>(estat.capActual);
        precio = new Double(estat.precio);
    }

	//Métodos
    public boolean isGoalState() {
        return false;
    }
    
    public ArrayList<Double> kg_restants() {
    	ArrayList<Double> kg_restants = new ArrayList<Double>();
    	for(int i = 1; i < capActual.size(); ++i){ 
    		kg_restants.set(i, trans.get(i).getPesomax() - capActual.get(i));
    	}
    	return kg_restants;
    }
    
    public ArrayList<Pair> paq_dies() {
    	int oferta;
    	ArrayList<Pair> paq_dies = new ArrayList<Pair>(); 
    	for(int j = 1; j < asignacion.size(); ++j) {
			oferta = asignacion.get(j);
			Pair pair = new Pair(j, trans.get(oferta).getDias());
			paq_dies.add(pair);
		}
    	return paq_dies;
    }
    
    public double pes_paquet(int paquet) {
    	return paq.get(paquet).getPeso();
    }
    
    public double preu_oferta_paquet(int paquet) {
    	return trans.get(asignacion.get(paquet)).getPrecio();
    }
    
    public int comparar_ofertes(double preu_actual, double pes_paquet, int j) {
    	boolean oferta_trobada = false;
    	double pespreu_nova;
    	for(int k = 0; k < capActual.size() && !oferta_trobada; ++k) {
			if(capActual.get(k) > pes_paquet && trans.get(k).getDias() == j-1) {
				pespreu_nova = trans.get(k).getPrecio() * pes_paquet;
				if(j-1 == 1) pespreu_nova = pespreu_nova + 2; //afegim nosaltres 2 al preu
				else if(j-1 == 2 || j -1 == 3 ) pespreu_nova = pespreu_nova - pes_paquet * 0.25;
				else if(j-1 == 5) pespreu_nova = pespreu_nova - pes_paquet * 0.5;
					if(preu_actual > pespreu_nova) return k;
			}
    	}
    	return 0;
    }
    
    public void modificar_capac(int paquet, double pes_paquet, int oferta) {
    	capActual.set(oferta, pes_paquet + capActual.get(oferta));
    	int oferta_anterior = asignacion.get(paquet);
    	capActual.set(oferta_anterior, capActual.get(oferta_anterior) - pes_paquet );
    	capActual.set(paquet, capActual.get(paquet) - pes_paquet);
    	asignacion.set(paquet, oferta);
    }
    
    public void calculaPrecio(){
        precio = 0.0;
        for(int i = 0; i < asignacion.size(); ++i){
            precio += trans.get(asignacion.get(i)).getPrecio()*paq.get(i).getPeso();
            if(trans.get(asignacion.get(i)).getDias() == 5){
                precio += 0.5*paq.get(i).getPeso();
            }
            else if(trans.get(asignacion.get(i)).getDias() >= 3){
                precio += 0.25*paq.get(i).getPeso();
            }
        }
    }
    
    public void calculaFelicidad() {
        
        for (int i = 0; i < asignacion.size(); i++) {
            int prioPaq = paq.get(i).getPrioridad();
            int diasOf = trans.get(asignacion.get(i)).getDias();
            if (prioPaq == 1 && diasOf == 1)  felicidad++;
            else if (prioPaq == 2) {
                if (diasOf == 3) felicidad++;
                else if (diasOf == 2) felicidad += 2;
                else if (diasOf == 1) felicidad += 3;
            }
        }
    }
    
    public void actFelicidad(int p, int od) {
        int prioPaq = paq.get(p).getPrioridad();
        int diasOfD = trans.get(od).getDias();
        int ofPaq = asignacion.get(p);
        if (ofPaq == -1) {
            if (prioPaq == 1 && diasOfD == 1) {
                felicidad++;
            } else if (prioPaq == 2) {
                if (diasOfD == 3) {
                    felicidad++;
                } else if (diasOfD == 2) {
                    felicidad += 2;
                } else if (diasOfD == 1) {
                    felicidad += 3;
                }
            }
        } else {
            int diasOf = trans.get(ofPaq).getDias();

            if (prioPaq == 1 && diasOfD == 1 && diasOf != 1) {
                felicidad++;
            } else if (prioPaq == 1 && diasOfD != 1 && diasOf == 1) {
                felicidad--;
            } else if (prioPaq == 2) {
                if (diasOf == 1) {
                    if (diasOfD > 3) {
                        felicidad -= 3;
                    } else if (diasOfD == 3) {
                        felicidad -= 2;
                    } else if (diasOfD == 2) {
                        felicidad--;
                    }
                } else if (diasOf == 2) {
                    if (diasOfD > 3) {
                        felicidad -= 2;
                    } else if (diasOfD == 3) {
                        felicidad--;
                    } else if (diasOfD == 1) {
                        felicidad++;
                    }
                } else if (diasOf == 3) {
                    if (diasOfD > 3) {
                        felicidad--;
                    } else if (diasOfD == 2) {
                        felicidad++;
                    } else if (diasOfD == 1) {
                        felicidad += 2;
                    }
                } else {
                    if (diasOfD == 3) {
                        felicidad++;
                    } else if (diasOfD == 2) {
                        felicidad += 2;
                    } else if (diasOfD == 1) {
                        felicidad += 3;
                    }
                }
            }
        }
    }
    
    public void restaFelicidad(int p) {
         int prioPaq = paq.get(p).getPrioridad();
         int diasOf = trans.get(asignacion.get(p)).getDias();
         if (prioPaq == 1 && diasOf == 1) felicidad--;
         else if (prioPaq == 2) {
             if (diasOf == 3) felicidad--;
             else if (diasOf == 2) felicidad -= 2;
             else if (diasOf == 1) felicidad -= 3;
         }
    }

    public void generaSolInicial1() {
        Collections.sort(paq, new Comparator<Paquete>() {
            @Override
            public int compare(Paquete p1, Paquete p2) {
                return ((Integer) p1.getPrioridad()).compareTo((Integer) p2.getPrioridad());
            }
        });

        ArrayList<Boolean> asignados = new ArrayList<Boolean>(paq.size());
        for (int i = 0; i < paq.size(); i++) {
            asignados.add(false);
        }

        int npaq = 0;
        for (int j = 0; j < 3; j++) {

            //System.out.println("Pasada " + j + "\n");

            for (int i = 0; i < trans.size(); i++) {

                //System.out.println("Oferta " + i + "\n");

                if (j == 0 && trans.get(i).getDias() == 1) {

                    while (capActual.get(i) < trans.get(i).getPesomax() && npaq < paq.size() && paq.get(npaq).getPrioridad() == 0) {
                        if (!asignados.get(npaq) && capActual.get(i) + paq.get(npaq).getPeso() <= trans.get(i).getPesomax()) {
                            asignacion.set(npaq, i);
                            asignados.set(npaq, true);
                            capActual.set(i, capActual.get(i) + paq.get(npaq).getPeso());
                            //System.out.println("Paquete " + npaq + " a oferta " + i + "\n");
                        } else {
                            if (asignados.get(npaq)) {
                                //System.out.println("Paquete " + npaq + " ya asignado :) \n");
                            } else {
                                //System.out.println("El paquete " + npaq + " no cabe :( \n");
                            }
                        }
                        npaq++;
                    }
                }
                if (j == 1 && trans.get(i).getDias() <= 3) {
                    while (capActual.get(i) < trans.get(i).getPesomax() && npaq < paq.size() && paq.get(npaq).getPrioridad() <= 1) {
                        if (!asignados.get(npaq) && capActual.get(i) + paq.get(npaq).getPeso() <= trans.get(i).getPesomax()) {
                            asignacion.set(npaq, i);
                            asignados.set(npaq, true);
                            capActual.set(i, capActual.get(i) + paq.get(npaq).getPeso());
                            //System.out.println("Paquete " + npaq + " a oferta " + i + "\n");
                        } else {
                            if (asignados.get(npaq)) {
                               // System.out.println("Paquete " + npaq + " ya asignado :) \n");
                            } else {
                                //System.out.println("El paquete " + npaq + " no cabe :( \n");
                            }
                        }
                        npaq++;
                    }
                }

                if (j == 2 && trans.get(i).getDias() <= 5) {
                    while (capActual.get(i) < trans.get(i).getPesomax() && npaq < paq.size() && paq.get(npaq).getPrioridad() <= 2) {
                        if (!asignados.get(npaq) && capActual.get(i) + paq.get(npaq).getPeso() <= trans.get(i).getPesomax()) {
                            asignacion.set(npaq, i);
                            asignados.set(npaq, true);
                            capActual.set(i, capActual.get(i) + paq.get(npaq).getPeso());
                            //System.out.println("Paquete " + npaq + " a oferta " + i + "\n");
                        } else {
                            if (asignados.get(npaq)) {
                                //System.out.println("Paquete " + npaq + " ya asignado :) \n");
                            } else {
                               // System.out.println("El paquete " + npaq + " no cabe :( \n");
                            }
                        }
                        npaq++;
                    }
                }
                npaq = 0;

            }

        }
        calculaPrecio();
        calculaFelicidad();
    }

    public boolean prioritatCorrecta(int dia, int priori){
        if(priori == 0 && dia == 1) return true;
        if(priori == 1 && dia <= 3) return true;
        if(priori == 2 && dia <= 5) return true;
        return false;
    }
    
    public void generaSolInicial2(){
        Collections.sort(paq, new Comparator<Paquete>() {
            @Override
            public int compare(Paquete p1, Paquete p2) {
                return ((Integer) p2.getPrioridad()).compareTo((Integer) p1.getPrioridad());
            }
        });
        
        Collections.sort(trans, new Comparator<Oferta>() {
            @Override
            public int compare(Oferta p1, Oferta p2) {
                return ((Integer) p2.getDias()).compareTo((Integer) p1.getDias());
            }
        }); 
        
        ArrayList<Boolean> asignados = new ArrayList<Boolean>(paq.size());
        for (int i = 0; i < paq.size(); i++) {
            asignados.add(false);
        }
        
        int npaq = 0;
        
        for(int i = 0; i < trans.size(); i++){
            while(capActual.get(i) < trans.get(i).getPesomax() && npaq < paq.size()){
                if(!asignados.get(npaq) && capActual.get(i)+paq.get(npaq).getPeso() <= trans.get(i).getPesomax()){
                    if(prioritatCorrecta(trans.get(i).getDias(), paq.get(npaq).getPrioridad())){
                        asignacion.set(npaq, i);
                        asignados.set(npaq, true);
                        capActual.set(i, capActual.get(i)+paq.get(npaq).getPeso());
                    }
                }
                ++npaq;
            }
            npaq = 0;
        }
        calculaPrecio();
        calculaFelicidad();
        
    }
    
   
    public void restaPrecio(int p){
        precio -= trans.get(asignacion.get(p)).getPrecio()*paq.get(p).getPeso();
        if(trans.get(asignacion.get(p)).getDias() == 5){
                precio -= 0.5*paq.get(p).getPeso();
            }
            else if(trans.get(asignacion.get(p)).getDias() >= 3){
                precio -= 0.25*paq.get(p).getPeso();
            }
    }
    
     public void sumaPrecio(int p){
        precio += trans.get(asignacion.get(p)).getPrecio()*paq.get(p).getPeso();
        if(trans.get(asignacion.get(p)).getDias() == 5){
                precio += 0.5*paq.get(p).getPeso();
            }
            else if(trans.get(asignacion.get(p)).getDias() >= 3){
                precio += 0.25*paq.get(p).getPeso();
            }
    }
    
    public boolean moverPaquete(int p, int o) {
        if (capActual.get(o) + paq.get(p).getPeso() <= trans.get(o).getPesomax() &&
        		prioritatCorrecta(trans.get(o).getDias(), paq.get(p).getPrioridad())) {
        	restaPrecio(p);
        	capActual.set(asignacion.get(p), capActual.get(asignacion.get(p))-paq.get(p).getPeso());
            capActual.set(o, capActual.get(o) + paq.get(p).getPeso());
            actFelicidad(p, o);
            asignacion.set(p, o);
            sumaPrecio(p);
            return true;
        }
        return false;
		//p -> id paquete en Paquetes, o -> id oferta en Transporte

    }

    public boolean permutarPaquetes(int p1, int p2) {
        int o1 = asignacion.get(p1);
        int o2 = asignacion.get(p2);
        if (capActual.get(o1) + paq.get(p2).getPeso() - paq.get(p1).getPeso() <= trans.get(o1).getPesomax()
                && capActual.get(o2) + paq.get(p1).getPeso() - paq.get(p2).getPeso() <= trans.get(o2).getPesomax()) {
            if (prioritatCorrecta(trans.get(o1).getDias(), paq.get(p2).getPrioridad()) &&
            		prioritatCorrecta(trans.get(o2).getDias(), paq.get(p1).getPrioridad())){	
            	restaPrecio(p1);
        		restaPrecio(p2);
        		capActual.set(o1, capActual.get(o1) + paq.get(p2).getPeso() - paq.get(p1).getPeso());
        		capActual.set(o2, capActual.get(o2) + paq.get(p1).getPeso() - paq.get(p2).getPeso());
        		actFelicidad(p1, o2);
                        actFelicidad(p2, o1);
                        asignacion.set(p1, o2);
        		asignacion.set(p2, o1);
        		sumaPrecio(p1);
        		sumaPrecio(p2);
        		return true;
        	}
        }
        return false;
        //p1/p2 -> id paquete p1/p2 en Paquetes
    }

    public boolean anadirPaquete(int p, int o) {
        if (capActual.get(o) + paq.get(p).getPeso() <= trans.get(o).getPesomax()) {
            capActual.set(o, capActual.get(o) + paq.get(p).getPeso());
            actFelicidad(p, o);
            asignacion.set(p, o);
            sumaPrecio(p);
            return true;
        }
        return false;
    }

    public void quitarPaquete(int p) {
        restaPrecio(p);
        int o = asignacion.get(p);
        capActual.set(o, capActual.get(o) - paq.get(p).getPeso());
        restaFelicidad(p);
        asignacion.set(p, -1);
    }

    public int hashFunction() {
        return 0;
    }

    public boolean equals(AzamonEstado ae) {
        return false;
    }

    public String toString() {
        StringBuffer resultado = new StringBuffer();
        resultado.append("ESTADO DEL ESTADO \n");
        resultado.append("Asignación: \n");
        for (int i = 0; i < asignacion.size(); i++) {
            resultado.append("Paquete " + i + ": " + paq.get(i).getPeso() + "kg/PR" + paq.get(i).getPrioridad());
            resultado.append(" - Oferta " + asignacion.get(i) + "\n");
        }
        resultado.append("Ofertas: \n");
        for (int i = 0; i < trans.size(); i++) {
            resultado.append("Oferta " + i + ": " + trans.get(i).getDias() + " días, " + capActual.get(i) + "/" + trans.get(i).getPesomax() + "kg, " + trans.get(i).getPrecio() + " €/kg \n");
        }
        resultado.append("PRECIO: "+precio+"\n");
        resultado.append("FELICIDAD: "+felicidad+"\n");
        return resultado.toString();
    }

    public double getCapActualOferta(int i) {
        return capActual.get(i);
    }

    public int getOfertaPaquete(int i) {
        return asignacion.get(i);
    }
    
    public String capActualToString() {
    	StringBuffer resultado = new StringBuffer();
    	resultado.append("CapActual:\n");
    	for (int i = 0; i < capActual.size(); ++i) {
    		resultado.append(i+" - "+capActual.get(i)+" // ");
    	}
    	resultado.append("\n");
    	return resultado.toString();
    }
    
    
    public String correspondenciasToString() {
    	StringBuffer resultado = new StringBuffer();
    	resultado.append("CORRESPONDENCIAS\n");
    	for (int i = 0; i < trans.size(); ++i) {
    		resultado.append("Oferta "+i+":");
    		for (int j = 0; j < paq.size(); ++j) {
    			if (asignacion.get(j) == i) resultado.append(" "+j);
    		}
    		resultado.append("\n");
    	}
    	return resultado.toString();
    }

}
