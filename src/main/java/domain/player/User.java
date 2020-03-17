package domain.player;

import domain.Result;

public class User extends Player {
	private final int bettingMoney;

	public User(String name, int bettingMoney) {
		super(name);
		this.bettingMoney = bettingMoney;
	}

	public Result compareScore(Gamer gamer) {
		int toCompareScore = gamer.calculateBurstIsZeroScore();
		int userScore = calculateBurstIsZeroScore();
		if (userScore > toCompareScore) {
			return Result.WIN;
		}
		if (userScore == toCompareScore) {
			return Result.DRAW;
		}
		return Result.LOSE;
	}
}
