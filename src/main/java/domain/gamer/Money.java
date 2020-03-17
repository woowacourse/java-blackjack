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

	private void validBettingMoney(int money) {
		if (money < 0) {
			throw new InvalidMoneyException();
		}
	}
}
