package blackjack.domain.gamer;

import java.util.List;

import blackjack.domain.BlackjackConstants;

public class Dealer extends BlackjackGamer {

	private static final String DEFAULT_DEALER_NAME = "딜러";

	public Dealer() {
		super(new Name(DEFAULT_DEALER_NAME));
	}

	@Override
	public boolean canReceiveCard() {
		return getScore() < BlackjackConstants.DEALER_MINIMUM_VALUE.getValue();
	}

	public int calculateWinCount(List<GameResult> playerResults) {
		return (int)playerResults.stream()
			.filter(GameResult::isLose)
			.count();
	}

	public int calculateLoseCount(List<GameResult> playerResults) {
		return (int)playerResults.stream()
			.filter(GameResult::isWin)
			.count();
	}
}
