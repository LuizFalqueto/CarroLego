package carro_lego;

//import lejos.utility.Delay;

public class UsoLuiz {

	public static void main(String[] args)
	{
		
		VeiculoSmart carro = new VeiculoSmart();
		
		carro.andaEAcerta();
		carro.segueLinha();
		
//		Delay.msDelay(1000);
//		carro.fechaGarra();
//		Delay.msDelay(1000);
//		carro.abreGarra();
		
		carro.ev3.beep5();
		carro.ev3.corLed(3);
	}
	
}