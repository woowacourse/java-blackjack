package domain.result.resultStrategy;

import static domain.gamer.Gamer.*;

public class WinStrategy implements MatchResultStrategy {
	@Override
	public boolean matchResultPredicate(int playerScore, int dealerScore) {
		return playerScore > dealerScore || dealerScore >= BUST_NUMBER;
	}
}
