package domain.player;

import domain.Result;
import domain.card.PlayerCards;

public class User extends Player {
	public User(String name) {
		validateName(name);
		this.name = name;
		this.playerCards = new PlayerCards();
	}

	private void validateName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("이름은 blank 값이 될 수 없습니다.");
		}
	}

	public Result beatDealer(Dealer dealer) {
		int dealerScore = dealer.calculateScore();
		int userScore = this.calculateScore();
		if (dealerScore > 21) {
			if (userScore > 21) {
				return Result.LOSE;
			}
			return Result.WIN;
		}
		if (userScore > 21) {
			return Result.LOSE;
		}
		if (dealerScore == userScore) {
			return Result.DRAW;
		}
		if (dealerScore > userScore) {
			return Result.LOSE;
		}
		return Result.WIN;
	}
}
