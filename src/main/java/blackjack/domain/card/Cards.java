package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

	private static final String NOT_ACE = "Ace 카드가 아닙니다.";

	public static final int BLACKJACK_SIZE = 2;
	private static final int BLACKJACK_NUMBER = 21;

	private final List<Card> values;

	public Cards() {
		this.values = new ArrayList<>();
	}

	public Cards(Card... cards) {
		values = new ArrayList<>(List.of(cards));
	}

	public void add(Card card) {
		values.add(card);
	}

	public int sum() {
		int sum = sumExceptAce();
		List<Card> aces = filterAces();
		return sumNotToBust(sum, aces);
	}

	private int sumExceptAce() {
		return values.stream()
			.filter(card -> !card.isAce())
			.mapToInt(Card::getValue)
			.sum();
	}

	private List<Card> filterAces() {
		return values.stream()
			.filter(Card::isAce)
			.collect(Collectors.toList());
	}

	private int sumNotToBust(int sum, List<Card> aces) {
		for (Card ace : aces) {
			sum += selectAceValue(sum, ace);
		}
		return sum;
	}

	private int selectAceValue(int sum, Card ace) {
		validateNotAce(ace);
		if (ace.getValue() + sum > BLACKJACK_NUMBER) {
			return CardNumber.LOWER_ACE_VALUE;
		}
		return ace.getValue();
	}

	private void validateNotAce(Card ace) {
		if (!ace.isAce()) {
			throw new IllegalArgumentException(NOT_ACE);
		}
	}

	public boolean isBlackJack() {
		return this.size() == BLACKJACK_SIZE &&
			this.sum() == BLACKJACK_NUMBER;
	}

	public boolean isBust() {
		return this.sum() > BLACKJACK_NUMBER;
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

	public List<Card> open(int count) {
		return values.subList(0, count);
	}

	public List<Card> getValues() {
		return Collections.unmodifiableList(values);
	}
}
