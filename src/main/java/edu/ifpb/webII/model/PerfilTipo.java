package edu.ifpb.webII.model;

public enum PerfilTipo {
    ADMIN(1, "ADMIN"),
    COORDENADOR(2, "COORDENADOR"),
    PROFESSOR(3, "PROFESSOR"),
    ESTUDANTE(4, "ESTUDANTE");

	private long cod;
    private final String desc;

	PerfilTipo(long cod, String desc) {
		this.cod = cod;
        this.desc = desc;
    }

	public long getCod() {
		return cod;
	}
	
    public String getDesc() {
        return desc;
    }
}