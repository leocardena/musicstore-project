package util;

public enum Estado {

	SP("São Paulo"), ES("Espírito Santo"), RJ("Rio de Janeiro"), CE("Ceará"), MG("Minas Gerais"), 
	GO("Goiás"), DF("Distrito Federal"), RS("Rio Grande do Sul"), RN("Rio Grande do Norte"),
	MT("Mato Grosso"), PR("Paraná"), SC("Santa Catarina"), BA("Bahia"), AM("Amazonas"), 
	AC("Acre"), TO("Tocantins"), AP("Amapá"), AL("Alagoas"), MA("Maranhão"), PA("Pará"),
	RO("Rondônia"), RR("Roraima"), SE("Sergipe"), MS("Mato Grosso do Sul"), PE("Pernambunco"), 
	PB("Paraíba"), PI("Piauí");
	
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
