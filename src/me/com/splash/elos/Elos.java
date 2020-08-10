package me.com.splash.elos;

public enum Elos {
	LEGENDARY("§4§l[✪]", 0, "§4§lLEGENDARY"),
	EXPERT("§9§l[✦]", 1, "§9§LEXPERT"),
	DIAMANTE("§b§l[✫]", 2, "§B§LDIAMANTE"),
	PLATINA("§d§l[✫]" , 3, "§D§LPLATINA"),
	OURO("§e§l[✫]", 4, "§E§LOURO"),
	PRATA("§f§l[✫]", 5 , "§F§LPRATA"),
	BRONZE("§6§l[✫]" , 6, "§6§LBRONZE"),
	UNRANKED("§7[-]", 7, "§7§lUNRANKED");
	
	private final String tag;
	private final int id;
	private final String view;
	
	private Elos(String tag, int id, String view) {
		this.tag = tag;
		this.id = id;
		this.view = view;
	}
	
	public String getTag() {return tag;}
	public int getQuantKils() {return id;}
	public String getViewer() {return view;}
	
	public int getKills() {
		int i = 1;
		switch(this) {
		case BRONZE:
			i = 1;
			break;
		case PRATA:
			i = 2;
			break;
		case OURO:
			i = 3;
			break;
		case PLATINA:
			i = 4;
			break;
		case DIAMANTE:
			i = 5;
			break;
		case EXPERT:
			i = 6;
			break;
		case LEGENDARY:
			i = 7;
			break;
		case UNRANKED:
			i = 0;
			break;
		}
	return i;
	}
}
