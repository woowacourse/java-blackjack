package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;

public class Gamer {

	public static final int ZERO_POINT = 0;

	private final List<Card> cards;

	public Gamer() {
		cards = new ArrayList<>();
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return new ArrayList<>(cards);
	}

	public boolean isOverThan(int number) {
		return getCardsNumberSum() > number;
	}

	public int getCardsNumberSum() {
		int sum = cards.stream()
				.filter(Card::isNotAce)
				.mapToInt(Card::getNumber)
				.sum();

		List<Card> aces = cards.stream()
				.filter(card -> !card.isNotAce())
				.collect(Collectors.toList());

		for (Card ace : aces) {
			if (sum + ace.getNumber() > 21) {
				sum += CardNumber.LOWER_ACE_VALUE;
			}
			else {
				sum += ace.getNumber();
			}
		}

		return sum;
	}
}
