package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.card.Card;

public abstract class BlackJackGamer {

	private final List<Card> cards;

	public BlackJackGamer(List<Card> cards) {
		this.cards = cards;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	/**
	 * ACE가 포함된 경우, 21 이하이면서 가장 가능한 큰 값으로 계산한다.
	 */
	public int sumCardNumbers() {
		int sum = cards.stream()
			.mapToInt(Card::getCardNumber)
			.sum();
		int aceCount = (int)cards.stream().filter(Card::isAce).count();

		for (int i = 0; i < aceCount; i++) {
			if (sum > 21) {
				sum -= 10;
			}
		}
		return sum;
	}

	public abstract boolean canReceiveCard();
}
