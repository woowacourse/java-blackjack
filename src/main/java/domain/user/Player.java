package domain.user;

import java.util.Arrays;
import java.util.Objects;

import domain.card.Card;
import domain.card.Cards;
import domain.result.MatchResult;

public class Player extends User {
	private static final int FIRST_SHOW_SIZE = 2;

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
		return new Player(new Name(name), new Cards(Arrays.asList(Objects.requireNonNull(cards))));
	}

	@Override
	public boolean isDrawable() {
		return !isBlackjack() && !isBust();
	}

	public MatchResult calculateMatchResult(Dealer dealer) {
		return MatchResult.calculatePlayerMatchResult(this, Objects.requireNonNull(dealer));
	}

	@Override
	protected int getFirstShowCardSize() {
		return FIRST_SHOW_SIZE;
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
