package blackjack.player.domain.component;

import java.util.Objects;

// todo: 테스트코드 추가 필요
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
		Objects.requireNonNull(bettingMoney, "배텅머니가 존재하지 않습니다");
		if (bettingMoney.isLessThan(MINIMUM_BETTING_MONEY)) {
			throw new IllegalArgumentException("배팅금액이 모자랍니다.");
		}
	}

	private void checkName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("플레이어의 이름이 존재하지 않습니다.");
		}
	}

	public String getName() {
		return name;
	}

	public Money calculateResultMoney(double profit) {
		return bettingMoney.multiply(profit);
	}
}
