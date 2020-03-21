package domain.result.resultStrategy;

import static domain.gamer.Money.*;

import domain.gamer.Money;

public class BlackJackStrategy implements MatchResultStrategy {
	@Override
	public boolean matchResultPredicate(int playerScore, int dealerScore) {
		return false;
	}

	@Override
	public Money earnCalculate(Money playerBettingMoney) {
		return playerBettingMoney.multiply(BLACKJACK_WIN_MONEY_RATIO);
	}
}
