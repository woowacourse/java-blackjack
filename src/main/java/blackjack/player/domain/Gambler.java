package blackjack.player.domain;

import java.util.Objects;

import blackjack.card.domain.CardBundle;

public class Gambler extends Player {
	public static final int MINIMUM_BETTING_MONEY = 1;
	private static final int BLACKJACK_MAXIMUM_VALUE = 21;
	private final Money bettingMoney;

	public Gambler(CardBundle cardBundle, String name, Money bettingMoney) {
		super(cardBundle, name);
		checkBettingMoney(bettingMoney);
		this.bettingMoney = bettingMoney;
	}

	private void checkBettingMoney(Money bettingMoney) {
		if (bettingMoney.isLessThan(MINIMUM_BETTING_MONEY)) {
			throw new IllegalArgumentException(String.format("배팅은 %d원 이상 해야합니다.", MINIMUM_BETTING_MONEY));
		}
	}

	public Money calculateProfit(double rate) {
		return bettingMoney.multiply(rate);
	}

	@Override
	public boolean isHit() {
		return cardBundle.calculateScore() < BLACKJACK_MAXIMUM_VALUE;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Gambler gambler = (Gambler)o;
		return Objects.equals(bettingMoney, gambler.bettingMoney);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), bettingMoney);
	}
}
