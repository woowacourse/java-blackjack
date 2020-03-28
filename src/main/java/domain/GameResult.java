package domain;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;

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

		players.getPlayers().forEach(this::makeResult);
		makeDealerResult(dealer);
	}

	public static GameResult of(Players players, Dealer dealer) {
		return new GameResult(players, dealer);
	}

	private void makeResult(Player player) {
		gameResult.put(player, player.calculateProfit());
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