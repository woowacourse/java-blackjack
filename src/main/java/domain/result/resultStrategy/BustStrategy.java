package domain.result.resultStrategy;

import static domain.gamer.Gamer.*;

import domain.gamer.Money;

public class BustStrategy implements MatchResultStrategy {
	@Override
	public boolean matchResultPredicate(int playerScore, int dealerScore) {
		return playerScore >= BUST_NUMBER;
	}

	@Override
	public Money earnCalculate(Money playerBettingMoney) {
		return playerBettingMoney.reversion();
	}
}
