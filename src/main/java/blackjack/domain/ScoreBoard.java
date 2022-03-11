package blackjack.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.dto.DealerResultDto;
import blackjack.domain.dto.PlayerResultDto;
import blackjack.domain.dto.ResultDto;

public class ScoreBoard {
	private static final String VICTORY = "승 ";
	private static final String DEFEAT = "패 ";
	private static final String DRAW = "무 ";

	public static Map<Player, Integer> of(Dealer dealer, List<Player> players) {
		Map<Player, Integer> result = new HashMap<>();
		players.forEach(player -> result.put(player, dealer.isWin(player)));
		return result;
	}

	public static ResultDto calculateResult1(Dealer dealer, List<Player> players) {
		List<Integer> dealerResult = new ArrayList<>();
		Map<String, String> playerResult = new HashMap<>();
		players.forEach(player -> playerResult.put(player.getName(),
			makePlayerOutcome(dealer.isWin(player))));

		for(Player player: players) {
			String name = player.getName();
			int result = dealer.isWin(player);

			playerResult.put(name, makePlayerOutcome(result));
			dealerResult.add(result);
		}

		DealerResultDto dealerResultDto = makeDealerOutcome(dealer.getName(), dealerResult);
		PlayerResultDto playerResultDto = new PlayerResultDto(playerResult);

		return new ResultDto(dealerResultDto, playerResultDto);
	}

	private static String makePlayerOutcome(int result) {
		if(result == 1) {
			return DEFEAT;
		}

		if(result == -1) {
			return VICTORY;
		}

		return DRAW;
	}

	private static DealerResultDto makeDealerOutcome(String name, List<Integer> dealerResult) {
		int winCount = 0;
		int drawCount = 0;
		int loseCount = 0;

		for(int result: dealerResult) {
			if (result == 1) {
				winCount++;
			}

			if (result == -1) {
				loseCount++;
			}

			if(result == 0) {
				drawCount++;
			}
		}

		return new DealerResultDto(name,
			makeDealerOutcomeFormat(winCount, drawCount, loseCount));
	}

	private static List<String> makeDealerOutcomeFormat(int winCount, int drawCount, int loseCount) {
		List<String> dealerOutcome = new ArrayList<>();

		if(winCount != 0) {
			dealerOutcome.add(winCount + VICTORY);
		}

		if(drawCount != 0) {
			dealerOutcome.add(drawCount + DRAW);
		}

		if(loseCount != 0) {
			dealerOutcome.add(loseCount + DEFEAT);
		}

		return dealerOutcome;
	}
}
