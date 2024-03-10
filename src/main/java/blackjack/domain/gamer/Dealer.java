package blackjack.domain.gamer;

import java.util.List;

public class Dealer extends BlackjackGamer {

	private static final String DEFAULT_DEALER_NAME = "딜러";
	private static final int DEALER_DRAW_THRESHOLD = 16;

	public Dealer() {
		super(new Name(DEFAULT_DEALER_NAME));
	}

	@Override
	public boolean canReceiveCard() {
		return getScore() <= DEALER_DRAW_THRESHOLD;
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
