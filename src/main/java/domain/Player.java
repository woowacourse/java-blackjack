package domain;

import java.util.List;

public class Player {
	private final Name name;
	private final CardHand cardHand;

	// TODO: 빈 리스트를 초기에 가지도록 하는 정팩메 만들기
	public Player(String name, List<Card> cardHand) {
		this.name = new Name(name);
		this.cardHand = new CardHand(cardHand);
	}

	public void receiveInitCards(List<Card> cards) {
		cardHand.add(cards);
	}

	public void receiveCard(Card card) {
		cardHand.add(card);
	}

	public boolean isBurst() {
		return cardHand.isBurst();
	}

	public String getName() {
		return name.getValue();
	}

	public List<Card> getCardHand() {
		return cardHand.getCards();
	}
}
