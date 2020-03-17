package domain.player;

import domain.Rule;

public class User extends Player {
	private static final int MINUS_CONVERTER = -1;
	private static final int ZERO_MONEY = 0;

	private final double bettingMoney;

	public User(String name, int bettingMoney) {
		super(name);
		this.bettingMoney = bettingMoney;
	}

	public boolean isPossibleAddCard() {
		return playerCards.calculateScore() < Rule.MAX_SCORE;
	}

	public double compareScore(Gamer gamerToCompare, boolean firstDrawBlackJack) {
		if (firstDrawBlackJack) {
			return blackJackCompare(gamerToCompare);
		}
		return normalCompare(gamerToCompare);
	}

	private double blackJackCompare(Gamer gamerToCompare) {
		if (!this.isBlackJack()) {
			return bettingMoney * MINUS_CONVERTER;
		}
		if (gamerToCompare.isBlackJack()) {
			return ZERO_MONEY;
		}
		return bettingMoney * Rule.BLACK_JACK_BONUS;
	}

	private double normalCompare(Gamer gamerToCompare) {
		int toCompareScore = gamerToCompare.calculateBurstIsZeroScore();
		int userScore = calculateBurstIsZeroScore();
		if (userScore > toCompareScore) {
			return bettingMoney;
		}
		if (userScore == toCompareScore) {
			return ZERO_MONEY;
		}
		return bettingMoney * MINUS_CONVERTER;
	}
}
