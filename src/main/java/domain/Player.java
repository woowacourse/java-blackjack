package domain;

import domain.card.Cards;

public class Player extends User {

	private double bet;

	public Player(final String name, Cards cards) {
		super(name, cards);
	}

	public void setBet(double bet){
		this.bet = bet;
	}

	public double getBet(){
		return bet;
	}
}
