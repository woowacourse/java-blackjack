package domain.gamer;

import domain.MatchResult;
import domain.card.Hands;
import domain.money.DefeatStrategy;
import domain.money.Money;
import domain.money.ProfitStrategy;

/**
 *    게임 플레이어 나타내는 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class Player extends Gamer {
	private Money bettingMoney;
	private ProfitStrategy profitStrategy;

	public Player(Name name, Money money) {
		super(name);
		bettingMoney = money;
		this.profitStrategy = new DefeatStrategy();
	}

	public void changeProfitStrategy(int dealerScore) {
		this.profitStrategy = MatchResult.getMatchResult(isWin(dealerScore), isPush(dealerScore), isBlackjack())
			.getProfitStrategy();
	}

	public double calculateProfit() {
		return this.profitStrategy.calculate(bettingMoney);
	}

	@Override
	public boolean canHit() {
		return scoreHands() < Hands.BLACKJACK_SCORE;
	}
}