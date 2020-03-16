package domain.player;

import domain.Result;

public class User extends Player {
	public User(String name) {
		super(name);
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
