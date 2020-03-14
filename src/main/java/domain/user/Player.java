package domain.user;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.card.Cards;
import domain.result.MatchResult;

public class Player extends User {
	private static final int INITIAL_START_INDEX = 0;
	private static final int INITIAL_FROM_INDEX = INITIAL_START_INDEX;
	private static final int INITIAL_TO_INDEX = 2;

	private Player(Name name) {
		super(name);
	}

	private Player(Name name, Cards cards) {
		super(name, cards);
	}

	public static Player valueOf(String name) {
		return new Player(new Name(name));
	}

	public static Player fromNameAndCards(String name, Card... cards) {
		return new Player(new Name(name), new Cards(Arrays.asList(cards)));
	}

	@Override
	public List<Card> getInitialCard() {
		return cards.getCards().subList(INITIAL_FROM_INDEX, INITIAL_TO_INDEX);
	}

	@Override
	public boolean isDrawable() {
		return !isBlackjack() && !isBust();
	}

	public MatchResult calculateMatchResult(Dealer dealer) {
		return MatchResult.calculatePlayerMatchResult(this, dealer);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Player that = (Player)object;
		return Objects.equals(this.name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
