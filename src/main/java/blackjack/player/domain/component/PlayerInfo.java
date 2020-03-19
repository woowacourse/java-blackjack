package blackjack.player.domain.component;

import java.util.Objects;

public class PlayerInfo {
	private static final int MINIMUM_BETTING_MONEY = 1;
	private final String name;
	private final Money bettingMoney;

	public PlayerInfo(String name, Money bettingMoney) {
		validate(name, bettingMoney);
		this.name = name;
		this.bettingMoney = bettingMoney;
	}

	private void validate(String name, Money bettingMoney) {
		checkBettingMoney(bettingMoney);
		checkName(name);
	}

	private void checkBettingMoney(Money bettingMoney) {
		Objects.requireNonNull(bettingMoney, "배팅머니가 존재하지 않습니다");
		if (bettingMoney.isLessThan(MINIMUM_BETTING_MONEY)) {
			throw new IllegalArgumentException("배팅금액이 모자랍니다.");
		}
	}

	private void checkName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("플레이어의 이름이 존재하지 않습니다.");
		}
	}

	public Money calculateResultMoney(double profit) {
		return bettingMoney.multiply(profit);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PlayerInfo that = (PlayerInfo)o;
		return Objects.equals(name, that.name) &&
			Objects.equals(bettingMoney, that.bettingMoney);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, bettingMoney);
	}

	public String getName() {
		return name;
	}
}
