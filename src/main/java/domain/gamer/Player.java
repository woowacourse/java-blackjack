package domain.gamer;

import java.util.List;

import domain.card.Card;

public class Player extends Gamer {
	private final Name name;

	// TODO: 빈 리스트를 초기에 가지도록 하는 정팩메 만들기
	public Player(String name, List<Card> cardHand) {
		super(cardHand);
		this.name = new Name(name);
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
