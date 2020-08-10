package me.com.splash.elos;


public class EloManager {
	
	private String name;
	private Elos elos;
	
	
	public EloManager setName(String name) {
		this.name = name;
		return this;
	}
	public EloManager setRank(Elos elos) {
		this.elos = elos;
		return this;
	}
	public String getName() {
		return name;
	}
	public Elos getElo() {
		return elos;
	}
	
	
}
