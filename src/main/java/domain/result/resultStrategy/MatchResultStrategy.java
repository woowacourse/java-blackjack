package domain.result.resultStrategy;

public interface MatchResultStrategy {
	boolean matchResultPredicate(int playerScore, int dealerScore);
}
