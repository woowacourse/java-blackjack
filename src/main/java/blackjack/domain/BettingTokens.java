package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class BettingTokens {
	private static final String CANNOT_ADD = "[ERROR] 전체 베팅 금액 수는 플레이어의 수보다 클 수 없습니다.";
	private final List<BettingToken> bettingMonies;
	private final int capacity;

	public BettingTokens(List<BettingToken> bettingMonies, int capacity) {
		this.bettingMonies = bettingMonies;
		this.capacity = capacity;
	}

	public BettingTokens(List<BettingToken> bettingMonies) {
		this.bettingMonies = bettingMonies;
		this.capacity = bettingMonies.size();
	}

	public BettingTokens(int capacity) {
		this(new ArrayList<>(), capacity);
	}

	public List<BettingToken> getBettingMonies() {
		return bettingMonies;
	}

	public int getTotalBettingMonies() {
		return bettingMonies.stream().mapToInt(BettingToken::getMoney).sum();
	}

	public void addBettingMoney(BettingToken bettingToken) {
		validateFullSize();
		this.bettingMonies.add(bettingToken);
	}

	private void validateFullSize() {
		if (this.bettingMonies.size() + 1 > this.capacity) {
			throw new IllegalArgumentException(CANNOT_ADD);
		}
	}
}
