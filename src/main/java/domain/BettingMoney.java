package domain;

import java.util.Objects;

public class BettingMoney {
	private static final int MIN = 0;
	public static final BettingMoney ZERO = new BettingMoney(MIN);

	private final int bettingMoney;

	public BettingMoney(int bettingMoney) {
		validate(bettingMoney);
		this.bettingMoney = bettingMoney;
	}

	private void validate(int bettingMoney) {
		if (bettingMoney < MIN) {
			throw new IllegalArgumentException("배팅 금액은 0 이상이어야 합니다.");
		}
	}

	public static BettingMoney from(String bettingMoney) {
		return new BettingMoney(Integer.parseInt(bettingMoney));
	}

	public int getValue() {
		return bettingMoney;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BettingMoney that = (BettingMoney)o;
		return bettingMoney == that.bettingMoney;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bettingMoney);
	}
}
