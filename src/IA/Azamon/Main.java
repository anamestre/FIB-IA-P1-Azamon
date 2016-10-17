package IA.Azamon;

public class Main {
	
	public static void main(String[] args){
		AzamonEstado estat = new AzamonEstado(100, 1234, 1.2, 1234);
		//estat.generaSolInicial1();
        estat.generarSolInicial2();
		System.out.println(estat.toString());
	}

}