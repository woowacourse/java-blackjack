package domain.player;

import domain.Result;

public class User extends Player {
	public User(String name) {
		super(name);
	}

	public Result compareScore(Dealer dealer) {
		int dealerScore = dealer.calculateBurstIsZeroScore();
		int userScore = calculateBurstIsZeroScore();
		if (userScore > dealerScore) {
			return Result.WIN;
		}
		if (userScore == dealerScore) {
			return Result.DRAW;
		}
		return Result.LOSE;
	}
}
