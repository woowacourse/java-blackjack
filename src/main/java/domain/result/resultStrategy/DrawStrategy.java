package domain.result.resultStrategy;

public class DrawStrategy implements MatchResultStrategy {
	@Override
	public boolean matchResultPredicate(int playerScore, int dealerScore) {
		return playerScore == dealerScore;
	}
}
