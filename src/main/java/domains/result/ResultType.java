package domains.result;

import java.util.function.BiFunction;

import domains.user.Dealer;
import domains.user.Player;
import domains.user.money.BettingMoney;
import domains.user.money.ProfitMoney;

public enum ResultType {
	BLACKJACK(1.5, ResultType::isBlackjack),
	WIN(1, ResultType::isWin),
	DRAW(0, ResultType::isDraw),
	LOSE(-1, ResultType::isLose);

	private double profitRate;
	private BiFunction<Player, Dealer, Boolean> judgeResultType;

	ResultType(double profitRate, BiFunction<Player, Dealer, Boolean> judgeResultType) {
		this.profitRate = profitRate;
		this.judgeResultType = judgeResultType;
	}

	public ResultType oppose() {
		if (this == WIN || this == BLACKJACK) {
			return LOSE;
		}
		if (this == LOSE) {
			return WIN;
		}
		return DRAW;
	}

	public ProfitMoney calculateProfitMoney(BettingMoney bettingMoney) {
		return bettingMoney.multiply(this.profitRate);
	}

	private static boolean isBlackjack(Player player, Dealer dealer) {
		return player.isBlackJack() && !dealer.isBlackJack();
	}

	private static boolean isDraw(Player player, Dealer dealer) {
		return player.isBlackJack() && dealer.isBlackJack() ||
			player.score() == dealer.score();
	}

	private static boolean isLose(Player player, Dealer dealer) {
		return player.isBurst() ||
			!player.isBlackJack() && dealer.isBlackJack() ||
			player.score() < dealer.score();
	}

	private static boolean isWin(Player player, Dealer dealer) {
		return !player.isBurst() && player.score() > dealer.score() ||
			!player.isBurst() && dealer.isBurst();
	}

	public BiFunction<Player, Dealer, Boolean> getJudgeResultType() {
		return judgeResultType;
	}
}
