package carro_lego;

public class VeiculoSmart extends Veiculo{
	public float angulo1;
	
	public VeiculoSmart() {
		super(false,true,true,false);
	}

	public void andaEAcerta(){
		super.dir.setSincronizacaoOn();
		super.esq.setSincronizacaoOn();
		super.setVelocidadeEsteirasGrau(240);
		super.setEsteirasForward();
		while(!super.isPreto("direito") || !super.isPreto("esquerdo"));
		if(super.isPreto("esquerdo")){
				super.curvaEsquerda();
				while(!super.isPreto("direito"));
				super.setVelocidadeEsteirasGrau(240);
				super.curvaDireita();
				while(super.isPreto("direito") && super.isPreto("esquerdo"));
				this.paraCarro();
		}else if(super.isPreto("direito")){
				super.curvaDireita();
				while(!super.isPreto("esquerda"));
				super.setVelocidadeEsteirasGrau(240);
				super.curvaEsquerda();
				while(super.isPreto("direito") && super.isPreto("esquerdo"));
				this.paraCarro();
			}		
	}
	
	public void segueLinha(){
		super.setVelocidadeEsteirasGrau(240);
		super.setEsteirasForward();
		while(super.isPreto("direito") && super.isPreto("esquerdo"));
	}

	public void segueLinhaAteBola(){
		super.setEsteirasForward();
		while(super.isPreto("direito") && super.isPreto("esquerdo") && super.getDistancia()>10);
		if(!super.isPreto("direito")){
			super.curvaEsquerda(1);
		}else if(!super.isPreto("esquerdo")){
			super.curvaDireita(1);
		}else if(super.getDistancia()<10){
			this.paraCarro();
		}
	}
	
	public void seguraBola(){
		super.abreGarra();
		if(super.getDistancia()<10){
			super.fechaGarra();
		}
	}
	
	public void largaBola(){
		super.abreGarra();
		super.setVelocidadeEsteirasGrau(240);
		super.setEsteirasBackward(1);
	}
	
	public void pegaBolaNalinha(){
		
	}
	
	public void voltaAoPontoDeOrigem(){
		
	}
	
	public void paraCarro(){
		super.stop();
	}
	
}
