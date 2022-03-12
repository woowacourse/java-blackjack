package blackjack.domain.card;

import static blackjack.domain.BlackJackGame.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
		if (ace.isAce() && ace.getValue() + sum > MAX_CARD_VALUE) {
			return CardNumber.LOWER_ACE_VALUE;
		}
		return ace.getValue();
	}

	public boolean isBlackJack() {
		return CardStatus.of(this).isBlackJack();
	}

	public boolean isBust() {
		return CardStatus.of(this).isBust();
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
