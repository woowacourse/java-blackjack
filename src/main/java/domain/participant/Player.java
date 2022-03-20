package domain.participant;

import domain.participant.info.Betting;
import domain.participant.info.Hand;
import domain.participant.info.Name;
import domain.result.WinOrLose;

public class Player extends Participant {

	public Player(Name name, Hand hand, Betting betting) {
		super(name, hand, betting);
	}

	public WinOrLose getResult(Dealer dealer) {
		if (dealer.isBlackJack() || isBlackJack()) {
			return getResultAtBlackJackExist(dealer);
		}

		if (dealer.isBust() || isBust()) {
			return getResultAtBustExist();
		}
		return judgeVersus(dealer.getScore());
	}

	private WinOrLose getResultAtBlackJackExist(Dealer dealer) {
		if (dealer.isBlackJack()) {
			return getResultAtDealerBlackJack();
		}
		return WinOrLose.BLACK_JACK_WIN;
	}

	private WinOrLose getResultAtDealerBlackJack() {
		if (isBlackJack()) {
			return WinOrLose.DRAW;
		}
		return WinOrLose.LOSE;
	}

	private WinOrLose getResultAtBustExist() {
		if (isBust()) {
			return WinOrLose.LOSE;
		}

		return WinOrLose.WIN;
	}

	private WinOrLose judgeVersus(int otherScore) {
		int myScore = getScore();
		if (myScore > otherScore) {
			return WinOrLose.WIN;
		}
		if (myScore < otherScore) {
			return WinOrLose.LOSE;
		}
		return WinOrLose.DRAW;
	}
}
