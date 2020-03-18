package blackjack.player.domain.component;

import java.util.Objects;

public class PlayerInfo {
	private final String name;
	private final Money bettingMoney;

	public PlayerInfo(String name, Money bettingMoney) {
		validate(name, bettingMoney);
		this.name = name;
		this.bettingMoney = bettingMoney;
	}

	private void validate(String name, Money bettingMoney) {
		Objects.requireNonNull(bettingMoney, "배텅머니가 존재하지 않습니다");
		checkName(name);
	}

	private void checkName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("플레이어의 이름이 존재하지 않습니다.");
		}
	}
}
