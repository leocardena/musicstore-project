package util;

public enum Estado {

	SP("S�o Paulo"), ES("Esp�rito Santo"), RJ("Rio de Janeiro"), CE("Cear�"), MG("Minas Gerais"), 
	GO("Goi�s"), DF("Distrito Federal"), RS("Rio Grande do Sul"), RN("Rio Grande do Norte"),
	MT("Mato Grosso"), PR("Paran�"), SC("Santa Catarina"), BA("Bahia"), AM("Amazonas"), 
	AC("Acre"), TO("Tocantins"), AP("Amap�"), AL("Alagoas"), MA("Maranh�o"), PA("Par�"),
	RO("Rond�nia"), RR("Roraima"), SE("Sergipe"), MS("Mato Grosso do Sul"), PE("Pernambunco"), 
	PB("Para�ba"), PI("Piau�");
	
	private String estado;
	
	Estado(String estado){
		this.estado = estado;
	}
	
	public String getEstado() {
		return estado;
	}

	@Override
	public String toString(){
		return getEstado();
	}
	
}
