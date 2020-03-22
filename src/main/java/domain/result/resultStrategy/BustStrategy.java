package domain.result.resultStrategy;

import static domain.gamer.Gamer.*;

public class BustStrategy implements MatchResultStrategy {
	@Override
	public boolean matchResultPredicate(int playerScore, int dealerScore) {
		return playerScore >= BUST_NUMBER;
	}
}
