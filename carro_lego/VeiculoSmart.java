package carro_lego;

import lejos.utility.Delay;
import java.lang.Math;

public class VeiculoSmart extends Veiculo{
	private float[] amostra = new float[3];
	
	public VeiculoSmart() {
		super(false,true,true,true);
	}

	
	// Metodo que reconhece linha preta
	// e ajusta o carro para o começo da atividade
	public void andaEAcerta(){
		
//			super.ligaSincronizacaoEsteiras();
//			super.abreGarra();
//			Delay.msDelay(1000);
			super.ev3.esperaSegundos(1);
		
			super.setVelocidadeEsteirasGrau(240);
			super.setEsteirasForward();
			while(!super.isPreto("direito") && !super.isPreto("esquerdo"));
			this.amostra[0]=this.pegaTacometro();
			this.zeraTacometro();
		    super.stop();
		    
		    if(super.isPreto("direito")&&!super.isPreto("esquerdo")){
		    	this.zeraTacometro();
		    	super.stop();
		    	super.setVelocidadeEsteirasGrau(240);
		    	super.curvaDireita();
		    	while(!super.isPreto("esquerdo") && super.isPreto("direito"));
		    	this.amostra[1] = (float) this.pegaTacometro()/ (float) (10.67);
		    	super.ligaSincronizacaoEsteiras();
		    	super.stop();
		    	this.zeraTacometro();
		    	
		    	super.setVelocidadeEsteirasGrau(200);
		    	super.setEsteirasForward();
		    	while(super.isPreto("direito") || super.isPreto("esquerdo"));
		    	super.stop();
		    	
		    	super.dir.ligaTras();
		    	super.esq.ligaFrente();
		    	while(super.isPreto("direito"));
		    	super.desligaSincronizacaoEsteiras();
		    	super.stop();
		    	
		    }else if(super.isPreto("esquerdo")&&!super.isPreto("direito")){
		    	this.zeraTacometro();
		    	super.stop();
		    	super.setVelocidadeEsteirasGrau(240);
		    	super.curvaEsquerda();
		    	while(super.isPreto("esquerdo") && !super.isPreto("direito"));
		    	this.amostra[1] = (float) this.pegaTacometro()/ (float) (10.67);
		    	super.ligaSincronizacaoEsteiras();
		    	super.stop();
		    	this.zeraTacometro();
		    	
		    	super.setVelocidadeEsteirasGrau(200);
		    	super.setEsteirasForward();
		    	while(super.isPreto("direito") || super.isPreto("esquerdo"));
		    	super.stop();
		    	
		    	super.dir.ligaFrente();
		    	super.esq.ligaTras();
		    	while(!super.isPreto("direito"));
		    	super.ligaSincronizacaoEsteiras();
		    	super.stop();
		    	
		    }else if(super.isPreto("direito")&&super.isPreto("esquerdo")){
		    	super.stop();
		    	super.setVelocidadeEsteirasGrau(240);
		    	super.setEsteirasForward();
		    	while(super.isPreto("direito") || super.isPreto("esquerdo"));
		    	
		    	super.dir.ligaFrente();
		    	super.esq.ligaTras();
		    	while(!super.isPreto("direito"));
		    	super.ligaSincronizacaoEsteiras();
		    	super.stop();		    	
		    }
	}
	
	//Metodo que percorre a linha ajustando o carro quando sai em um dos lados da linha
	//e chama o metodo de segurar a bola
	public void segueLinha(){
			//this.zeraTacometro();
			super.setVelocidadeEsteirasGrau(240);
			super.setEsteirasForward();
			while(super.isPreto("direito") && super.isPreto("esquerdo"));
			this.amostra[2]=this.pegaTacometro();
			
			if(!super.isPreto("esquerdo")&&!super.isPreto("direito")){
				if(super.getDistancia()<10){
					super.stop();
					this.seguraBola();
					this.voltaAoPontoDeOrigem();
				}else{
					super.dir.ligaFrente();
					super.esq.ligaTras();
					while(!super.isPreto("direito")||!super.isPreto("esquerdo"));
					int andarDurante = (int) this.amostra[2]/240;
					super.setEsteirasForward(andarDurante);
				}
				
			}else if(!super.isPreto("esquerdo") && super.isPreto("direito")){
				
				super.curvaDireita();
				while(!super.isPreto("esquerdo") && super.isPreto("direito"));
				this.segueLinha();				
				
			}else if(super.isPreto("esquerdo") && !super.isPreto("direito")){
				
				super.curvaEsquerda();
				while(super.isPreto("esquerdo") && !super.isPreto("direito"));
				this.segueLinha();
				
			}
		}
	
	public void seguraBola(){
		super.fechaGarra(); 
		Delay.msDelay(1000);
		super.abreGarra();
		//super.ev3.esperaSegundos(1);
		super.setVelocidadeEsteirasGrau(150);
		super.setEsteirasForward();
		//super.ev3.esperaSegundos(1);
		Delay.msDelay(1000);
		super.fechaGarra();
	}
	
	public void largaBola(){
		super.abreGarra();
		super.setVelocidadeEsteirasGrau(240);
		super.setEsteirasBackward(1);
	}
	
	public float pegaTacometro(){
		return super.dir.getTacometro()+super.esq.getTacometro();
	}
	
	public void zeraTacometro(){
		super.dir.resetTacometro();
		super.esq.resetTacometro();
	}
	
	public void voltaAoPontoDeOrigem(){
		float a = this.amostra[0];
		float b = this.amostra[2];		
		float c = (float) Math.sqrt(Math.pow(a,2) + Math.pow(b,2) - 2*(a*b)*Math.cos(this.amostra[1]));
		
		float anguloParaVirar = (float) (180 - ((Math.pow(a,2)-Math.pow(b,2)-Math.pow(c,2))/2*b*c));
		
		int tempoParaVirar = (int) (anguloParaVirar/240);
		
		super.curvaDireita(tempoParaVirar);
		
		super.setVelocidadeEsteirasGrau(240);
		super.setEsteirasForward((int)c/240);
		
		this.largaBola();
	}
	
}
