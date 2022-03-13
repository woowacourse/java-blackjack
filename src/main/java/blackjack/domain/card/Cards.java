package blackjack.domain.card;

import static blackjack.domain.BlackJackGame.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.BlackJackGame;

public class Cards {

	private final List<Card> values;

	public Cards() {
		this.values = new ArrayList<>();
	}

	public void add(Card card) {
		values.add(card);
	}

	public int sum() {
		int sum = getSumExceptAce();
		List<Card> aces = getAces();
		return getSumNotToBust(sum, aces);
	}

	private int getSumExceptAce() {
		return values.stream()
			.filter(card -> !card.isAce())
			.mapToInt(Card::getValue)
			.sum();
	}

	private List<Card> getAces() {
		return values.stream()
			.filter(Card::isAce)
			.collect(Collectors.toList());
	}

	private int getSumNotToBust(int sum, List<Card> aces) {
		for (Card ace : aces) {
			sum += selectAceValue(sum, ace);
		}
		return sum;
	}

	private int selectAceValue(int sum, Card ace) {
		validateNotAce(ace);
		if (ace.getValue() + sum > MAX_CARD_VALUE) {
			return CardNumber.LOWER_ACE_VALUE;
		}
		return ace.getValue();
	}

	private void validateNotAce(Card ace) {
		if (!ace.isAce()) {
			throw new IllegalArgumentException("Ace 카드가 아닙니다.");
		}
	}

	public boolean isBlackJack() {
		return this.size() == BlackJackGame.INIT_DISTRIBUTION_COUNT &&
			this.sum() == BlackJackGame.MAX_CARD_VALUE;
	}

	public boolean isBust() {
		return this.sum() > BlackJackGame.MAX_CARD_VALUE;
	}

	public boolean isGreaterThan(Cards cards) {
		return this.sum() > cards.sum();
	}

	public int size() {
		return values.size();
	}

	public boolean isSame(Cards dealer) {
		return this.sum() == dealer.sum();
	}

	public List<Card> getValues() {
		return Collections.unmodifiableList(values);
	}
}
