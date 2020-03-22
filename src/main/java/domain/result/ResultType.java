package domain.result;

import java.util.function.DoubleUnaryOperator;

public enum ResultType {
	BLACKJACK_WIN(bettingMoney -> bettingMoney * 1.5),
	WIN(bettingMoney -> bettingMoney * 1),
	DRAW(bettingMoney -> 0),
	LOSE(bettingMoney -> bettingMoney * -1);

	private final DoubleUnaryOperator bettingMoneyExchanger;

	ResultType(DoubleUnaryOperator bettingMoneyExchanger) {
		this.bettingMoneyExchanger = bettingMoneyExchanger;
	}

	public Double getExchangedBettingMoney(Double bettingMoney) {
		return bettingMoneyExchanger.applyAsDouble(bettingMoney);
	}
}