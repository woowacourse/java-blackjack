package domain.player;

import domain.Money;
import domain.Rule;

public class User extends Player {
	private final Money money;
	private boolean isInitCardBlackJack = false;

	public User(String name, Money money) {
		super(name);
		this.money = money;
	}

	public boolean isPossibleAddCard() {
		return playerCards.calculateScore() < Rule.MAX_SCORE;
	}

	public double compareScore(Gamer gamerToCompare) {
		if (isInitCardBlackJack) {
			return blackJackCompare(gamerToCompare);
		}
		return normalCompare(gamerToCompare);
	}

	private double blackJackCompare(Gamer gamerToCompare) {
		if (gamerToCompare.isBlackJack()) {
			return Money.ZERO;
		}
		return money.toBlackJackWinMoney();
	}

	private double normalCompare(Gamer gamerToCompare) {
		int toCompareScore = gamerToCompare.calculateBurstIsZeroScore();
		int userScore = calculateBurstIsZeroScore();
		if (userScore > toCompareScore) {
			return money.getMoney();
		}
		if (userScore == toCompareScore) {
			return Money.ZERO;
		}
		return money.toLoseMoney();
	}

	public void checkInitCardBlackJack() {
		this.isInitCardBlackJack = this.isBlackJack();
	}
}
