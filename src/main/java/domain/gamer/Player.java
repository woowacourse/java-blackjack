package domain.gamer;

import java.util.List;

import domain.card.Card;
import domain.card.CardHand;

public class Player extends Gamer {
	private final Name name;

	private Player(String name, CardHand cardHand) {
		super(cardHand);
		this.name = new Name(name);
	}

	public static Player newInstance(String name) {
		return new Player(name, CardHand.createEmpty());
	}

	public static Player of(String name, List<Card> cards) {
		return new Player(name, CardHand.from(cards));
	}

	public void receiveCard(Card card) {
		cardHand.add(card);
	}

	public boolean isBust() {
		return cardHand.isBurst();
	}

	public String getName() {
		return name.getValue();
	}
}
