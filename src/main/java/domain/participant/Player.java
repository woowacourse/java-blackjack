package domain.participant;

import domain.participant.info.Betting;
import domain.participant.info.Hand;
import domain.participant.info.Name;
import domain.result.EarningRate;

public class Player extends Participant {

	public Player(Name name, Hand hand, Betting betting) {
		super(name, hand, betting);
	}

	public EarningRate getResult(Dealer dealer) {
		if (dealer.isBlackJack() || isBlackJack()) {
			return getResultAtBlackJackExist(dealer);
		}

		if (dealer.isBust() || isBust()) {
			return getResultAtBustExist();
		}
		return judgeVersus(dealer.getScore());
	}

	private EarningRate getResultAtBlackJackExist(Dealer dealer) {
		if (dealer.isBlackJack()) {
			return getResultAtDealerBlackJack();
		}
		return EarningRate.BLACK_JACK_WIN;
	}

	private EarningRate getResultAtDealerBlackJack() {
		if (isBlackJack()) {
			return EarningRate.DRAW;
		}
		return EarningRate.LOSE;
	}

	private EarningRate getResultAtBustExist() {
		if (isBust()) {
			return EarningRate.LOSE;
		}

		return EarningRate.WIN;
	}

	private EarningRate judgeVersus(int otherScore) {
		int myScore = getScore();
		if (myScore > otherScore) {
			return EarningRate.WIN;
		}
		if (myScore < otherScore) {
			return EarningRate.LOSE;
		}
		return EarningRate.DRAW;
	}
}
