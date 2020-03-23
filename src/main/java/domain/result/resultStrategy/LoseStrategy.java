package domain.result.resultStrategy;

public class LoseStrategy implements MatchResultStrategy {
	@Override
	public boolean matchResultPredicate(int playerScore, int dealerScore) {
		return playerScore < dealerScore;
	}
}
