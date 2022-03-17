package domain.participant;

import domain.card.Hand;
import domain.result.WinOrLose;

public class Player extends Participant {

	public Player(Name name, Hand hand, Betting betting) {
		super(name, hand, betting);
	}

	public WinOrLose getResult(Dealer other) {
		if (other.isBlackJack()) {
			return getResultAtDealerBlackJack();
		}

		if (isBlackJack()) {

		}
		if (isBust()) {
			return WinOrLose.LOSE;
		}

		if (isBlackJack() || other.isBust()) {
			return WinOrLose.WIN;
		}

		return judgeVersus(other.getBestScore());
	}

	private WinOrLose getResultAtDealerBlackJack() {
		if (isBlackJack()) {
			return WinOrLose.DRAW;
		}
		return WinOrLose.LOSE;
	}

	private WinOrLose judgeVersus(int otherScore) {
		int myScore = getBestScore();
		if (myScore > otherScore) {
			return WinOrLose.WIN;
		}
		if (myScore < otherScore) {
			return WinOrLose.LOSE;
		}
		return WinOrLose.DRAW;
	}
}
