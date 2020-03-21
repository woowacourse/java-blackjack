package domain.result.resultStrategy;

import domain.gamer.Money;

public class DrawStrategy implements MatchResultStrategy {
	@Override
	public boolean matchResultPredicate(int playerScore, int dealerScore) {
		return playerScore == dealerScore;
	}

	@Override
	public Money earnCalculate(Money playerBettingMoney) {
		return playerBettingMoney.getZeroMoney();
	}
}
