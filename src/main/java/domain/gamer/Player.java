package domain.gamer;

import domain.card.Hands;
import domain.money.Money;

/**
 *    게임 플레이어 나타내는 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class Player extends Gamer {
	private Money bettingMoney;

	public Player(Name name, Money money) {
		super(name);
		bettingMoney = money;
	}

	@Override
	public boolean canHit() {
		return scoreHands() < Hands.BLACKJACK_SCORE;
	}

	public Money getBettingMoney() {
		return bettingMoney;
	}
}
