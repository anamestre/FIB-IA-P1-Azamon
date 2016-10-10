package IA.Azamon;

public class Main {
	
	public void main(){
		AzamonEstado estat = new AzamonEstado(100, 1234, 1.2, 1234);
		estat.generaSolInicial1();
		System.out.println(estat.toString());	
	}

}
