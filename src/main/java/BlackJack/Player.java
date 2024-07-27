package BlackJack;

public class Player {

	private String name;
	private Card cards;
	
	public Player(String name) {
		this.name = name;
		this.cards = new Card();
	}
	
	public String getName() {
		return name;
	}
	
	public Card getCards() {
		return cards;
	}
	
	public void setCards(String c) {
		this.cards.addCard(c);
	}
	
	
	
}
