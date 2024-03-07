package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.dto.CardDto;

public class Hand {

	private final List<Card> cards;

	public Hand(List<Card> cards) {
		this.cards = cards;
	}

	public void add(Card card) {
		cards.add(card);
	}

	/**
	 * ACE가 포함된 경우, 21 이하이면서 가장 가능한 큰 값으로 계산한다.
	 */
	public int sum() {
		int sum = cards.stream()
			.mapToInt(Card::getNumber)
			.sum();
		int aceCount = (int)cards.stream().filter(Card::isAce).count();

		for (int i = 0; i < aceCount; i++) {
			if (sum > 21) {
				sum -= 10;
			}
		}
		return sum;
	}

	public Card getFirstCard() {
		return cards.get(0);
	}

	public List<CardDto> convertHandToDto() {
		return cards.stream()
			.map(Card::convertCardToDto)
			.toList();
	}
}
