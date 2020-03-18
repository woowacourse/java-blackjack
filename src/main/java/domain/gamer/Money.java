package domain.gamer;

import exception.InvalidMoneyException;

public class Money {
	private int money;

	public Money(String inputMoney) {
		try {
			int money = Integer.parseInt(inputMoney);
			validBettingMoney(money);
			this.money = money;
		} catch (InvalidMoneyException | NumberFormatException e) {
			throw new InvalidMoneyException();
		}
	}

	private Money(int money) {
		this.money = money;
	}

	private void validBettingMoney(int money) {
		if (money < 0) {
			throw new InvalidMoneyException();
		}
	}

	public Money reversion() {
		return new Money(-money);
	}

	public Money getZeroMoney() {
		return new Money(0);
	}

	public Money multiply(double operand) {
		return new Money((int)(money * operand));
	}

	public int getMoney() {
		return money;
	}
}
