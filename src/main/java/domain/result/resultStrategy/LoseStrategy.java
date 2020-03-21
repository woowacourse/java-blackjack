package domain.result.resultStrategy;

import domain.gamer.Money;

public class LoseStrategy implements MatchResultStrategy {
	@Override
	public boolean matchResultPredicate(int playerScore, int dealerScore) {
		return playerScore < dealerScore;
	}

	@Override
	public Money earnCalculate(Money playerBettingMoney) {
		return playerBettingMoney.reversion();
	}
}
