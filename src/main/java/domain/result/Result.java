package domain.result;

import java.util.LinkedHashMap;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;

public class Result {

	private final LinkedHashMap<Participant, WinOrLose> playerResults;

	private Result(LinkedHashMap<Participant, WinOrLose> playerResults) {
		this.playerResults = playerResults;
	}

	public static Result of(Dealer dealer, Players players) {
		LinkedHashMap<Participant, WinOrLose> result = new LinkedHashMap<>();
		players.forEach(player -> result.putIfAbsent(player, judgePlayerWinOrLose(dealer, player)));
		return new Result(result);
	}

	public int getDealerMoney() {
		return -1 * playerResults.entrySet().stream()
			.mapToDouble(entry -> entry.getKey().getBettingMoney() * entry.getValue().getEarningRate())
			.mapToInt(money -> (int)money)
			.sum();
	}

	public int getPlayerMoney(Participant player) {
		return (int)(player.getBettingMoney() * playerResults.get(player).getEarningRate());
	}

	private static WinOrLose judgePlayerWinOrLose(Dealer dealer, Participant player) {
		if (dealer.isBlackJack() || player.isBlackJack()) {
			return judgeWinOrLoseAtBlackJackExist(dealer, player);
		}

		if (dealer.isBust() || player.isBust()) {
			return judgeWinOrLoseAtBustExist(player);
		}
		return judgeWinOrLoseByScore(dealer, player);
	}

	private static WinOrLose judgeWinOrLoseAtBlackJackExist(Dealer dealer, Participant player) {
		if (dealer.isBlackJack()) {
			return judgeWinOrLoseAtDealerBlackJack(player);
		}
		return WinOrLose.BLACK_JACK_WIN;
	}

	private static WinOrLose judgeWinOrLoseAtDealerBlackJack(Participant player) {
		if (player.isBlackJack()) {
			return WinOrLose.DRAW;
		}
		return WinOrLose.LOSE;
	}

	private static WinOrLose judgeWinOrLoseAtBustExist(Participant player) {
		if (player.isBust()) {
			return WinOrLose.LOSE;
		}

		return WinOrLose.WIN;
	}

	private static WinOrLose judgeWinOrLoseByScore(Dealer dealer, Participant player) {
		int playerScore = player.getScore();
		int dealerScore = dealer.getScore();

		if (playerScore > dealerScore) {
			return WinOrLose.WIN;
		}

		if (playerScore < dealerScore) {
			return WinOrLose.LOSE;
		}
		return WinOrLose.DRAW;
	}
}
