package domain.user;

import static domain.score.Score.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import domain.card.Card;
import domain.card.Cards;
import domain.result.MatchResult;
import domain.result.Prize;

public class Player extends User {
	private static final int FIRST_SHOW_SIZE = 2;

	private final Money bettingMoney;

	private Player(Name name, Money bettingMoney) {
		this(name, bettingMoney, new Cards(new ArrayList<>()));
	}

	private Player(Name name, Money bettingMoney, Cards cards) {
		super(name, cards);
		this.bettingMoney = bettingMoney;
	}

	public static Player fromNameAndMoney(String name, int bettingMoney) {
		return new Player(new Name(name), Money.valueOf(bettingMoney));
	}

	public static Player fromNameAndMoneyAndCards(String name, int bettingMoney, Card... cards) {
		return new Player(new Name(name), Money.valueOf(bettingMoney),
			new Cards(Arrays.asList(Objects.requireNonNull(cards))));
	}

	public Prize calculatePlayerPrize(Dealer other) {
		MatchResult matchResult = MatchResult.calculatePlayerMatchResult(this, other);
		return matchResult.calculatePrize(bettingMoney);
	}

	@Override
	public boolean isDrawable() {
		return !isBlackjack() && calculateScore().isSmallerThan(ofValue(VALID_MAXIMUM_SCORE_VALUE));
	}

	@Override
	public int getFirstShowCardSize() {
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
