package blackjack.domain.participant;

import static blackjack.domain.game.WinnerFlag.*;

import blackjack.domain.card.Deck;
import blackjack.domain.game.WinnerFlag;

public class Player extends Gamer {
	public static final int THRESHOLD_OF_BURST = 21;
	private static final String ERROR_MESSAGE_OF_Y_OR_N = "y 혹은 n 만 입력하여 주십시오.";
	private static final String AGREE = "y";
	private static final String DISAGREE = "n";

	private WinnerFlag result;

	public Player(String name) {
		super(name);
	}

	@Override
	public boolean canReceiveCard() {
		return !playerState.isFinished();
	}

	@Override
	public boolean continueDraw(String draw, Deck deck) {
		if (!AGREE.equals(draw) && !DISAGREE.equals(draw)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_OF_Y_OR_N);
		}
		if (AGREE.equals(draw)) {
			playerState = playerState.keepContinue(true);
		}
		if (DISAGREE.equals(draw)) {
			playerState = playerState.keepContinue(false);
		}
		return canReceiveCard();
	}

	public WinnerFlag calculateResult(Dealer dealer) {
		/*if (dealer.playerState.isBurst() && playerState.isBurst() || dealer.isSameThan(playerPoints)) {
			matchResult(DRAW);
		}
		if (isBurst(playerPoints) || (!dealer.isBurst(dealerPoints) && dealer.isBiggerThan(playerPoints))) {
			matchResult(LOSE);
		}
		if ((dealer.isSmallerThan(playerPoints) && !isBurst(playerPoints)) || dealer.isBurst(dealerPoints)) {
			matchResult(WIN);
		}
		return result;
		*/
		return WIN;
	}

	public void matchResult(WinnerFlag result) {
		this.result = result;
	}

	public WinnerFlag getResult() {
		return result;
	}
}
