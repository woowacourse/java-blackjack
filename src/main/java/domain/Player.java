package domain;

import java.util.List;

public class Player {
	private final Name name;
	private final List<Card> cardHand;

	// TODO: 빈 리스트를 초기에 가지도록 하는 정팩메 만들기
	public Player(String name, List<Card> cardHand) {
		this.name = new Name(name);
		this.cardHand = cardHand;
	}

	public void receiveInitCards(List<Card> cards) {
		cardHand.addAll(cards);
	}

	public List<Card> getCardHand() {
		return cardHand;
	}

	public String getName() {
		return name.getValue();
	}
}
