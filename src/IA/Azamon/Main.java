package IA.Azamon;

public class Main {
	
	public static void main(String[] args){
		System.out.println("Hola sóc un programa rebel i no vull escriure res\n");
		AzamonEstado estat = new AzamonEstado(100, 1234, 1.2, 1234);
		System.out.println("T'he dit que no imprimiria res per què em fas fer això\n");
		estat.generaSolInicial1();
		System.out.println("Em pots fer generar coses però no les imprimiré\n");
		System.out.println(estat.toString());
		System.out.println("Chapucero\n");
	}

}
