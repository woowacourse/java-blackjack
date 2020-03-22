package domain;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.money.BlackjackWinStrategy;
import domain.money.DefeatStrategy;
import domain.money.PushStrategy;
import domain.money.WinStrategy;

/**
 *    게임 결과 클래스입니다.
 *
 *    @author AnHyungJu
 */
public class GameResult {
	private static final double INITIAL_PROFIT = 0.0D;
	private static final int TO_NEGATIVE = -1;

	private Map<Gamer, Double> gameResult;

	private GameResult(Players players, Dealer dealer) {
		gameResult = new LinkedHashMap<>();

		players.getPlayers().forEach(player -> makeResult(dealer, player));
		makeDealerResult(dealer);
	}

	public static GameResult of(Players players, Dealer dealer) {
		return new GameResult(players, dealer);
	}

	private void makeResult(Dealer dealer, Player player) {
		gameResult.put(player, new DefeatStrategy().calculate(player.getBettingMoney()));
		if (player.isPush(dealer.scoreHands())) {
			gameResult.put(player, new PushStrategy().calculate(player.getBettingMoney()));
		}
		if (player.isWin(dealer.scoreHands())) {
			gameResult.put(player, new WinStrategy().calculate(player.getBettingMoney()));
		}
		if (player.isWin(dealer.scoreHands()) && player.isBlackjack() && !dealer.isBlackjack()) {
			gameResult.put(player, new BlackjackWinStrategy().calculate(player.getBettingMoney()));
		}
	}

	private void makeDealerResult(Dealer dealer) {
		double dealerProfit = INITIAL_PROFIT;
		for (Gamer player : gameResult.keySet()) {
			dealerProfit += gameResult.get(player);
		}
		gameResult.put(dealer, dealerProfit * TO_NEGATIVE);
	}

	public Map<Gamer, Double> getGameResult() {
		return gameResult;
	}
}