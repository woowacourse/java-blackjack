package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class BettingMoneys {
	private static final String CANNOT_ADD = "[ERROR] 전체 베팅 금액 수는 플레이어의 수보다 클 수 없습니다.";
	private final List<BettingMoney> bettingMonies;
	private final int capacity;

	public BettingMoneys(List<BettingMoney> bettingMonies, int capacity) {
		this.bettingMonies = bettingMonies;
		this.capacity = capacity;
	}

	public BettingMoneys(int capacity) {
		this(new ArrayList<>(), capacity);
	}

	public void addBettingMoney(BettingMoney bettingMoney) {
		validateFullSize();
		this.bettingMonies.add(bettingMoney);
	}

	private void validateFullSize() {
		if (this.bettingMonies.size() + 1 > this.capacity) {
			throw new IllegalArgumentException(CANNOT_ADD);
		}
	}
}
