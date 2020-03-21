package domain.result.resultStrategy;

import domain.gamer.Money;

public interface MatchResultStrategy {
	boolean matchResultPredicate(int playerScore, int dealerScore);
	Money earnCalculate(Money playerBettingMoney);
}
