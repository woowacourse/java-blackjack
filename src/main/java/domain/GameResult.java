package domain;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;

/**
 *    게임 결과 클래스입니다.
 *
 *    @author AnHyungJu
 */
public class GameResult {
	private static final String NEW_LINE = "\n";
	private static final String WIN = "승";
	private static final String DRAW = "무";
	private static final String DEFEAT = "패";
	private static final String DASH = " - ";
	private static final String COLON = ": ";
	private static final String COMMA = ", ";

	private String gameResult;

	private GameResult(BlackjackGame blackjackGame) {
		Dealer dealer = blackjackGame.getDealer();
		List<Player> players = blackjackGame.getPlayers();

		this.gameResult = gameScore(dealer, players) + matchResult(dealer, players);
	}

	public static GameResult of(BlackjackGame blackjackGame) {
		return new GameResult(blackjackGame);
	}

	private String gameScore(Dealer dealer, List<Player> players) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(cardsOf(dealer))
			.append(DASH)
			.append(scoreOf(dealer)).append(NEW_LINE);
		for (Player player : players) {
			stringBuilder.append(cardsOf(player)).append(DASH)
				.append(scoreOf(player)).append(NEW_LINE);
		}
		return stringBuilder.toString();
	}

	private String cardsOf(Gamer gamer) {
		return gamer.getName()
			+ COLON
			+ gamer.getHands().getCards().stream()
			.map(Card::shape)
			.collect(Collectors.joining(COMMA));
	}

	private int scoreOf(Gamer gamer) {
		return gamer.scoreHands();
	}

	private String matchResult(Dealer dealer, List<Player> players) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("## 최종 승패\n");
		stringBuilder.append(recordOf(dealer, players));
		for (Player player : players) {
			stringBuilder.append(findMatchResult(dealer, player))
				.append(NEW_LINE);
		}
		return stringBuilder.toString();
	}

	private String recordOf(Dealer dealer, List<Player> players) {
		int defeatCount = dealerDefeatCount(dealer, players);
		int drawCount = dealerDrawCount(dealer, players);
		int winCount = players.size() - defeatCount - drawCount;
		return "딜러: " + winCount + WIN + COMMA + drawCount + DRAW + COMMA + defeatCount + DEFEAT + NEW_LINE;
	}

	private int dealerDefeatCount(Dealer dealer, List<Player> players) {
		return (int)players.stream()
			.filter(player -> player.wins(dealer.scoreHands()) || player.isPush(dealer.scoreHands()))
			.count();
	}

	private int dealerDrawCount(Dealer dealer, List<Player> players) {
		return (int)players.stream()
			.filter(player -> player.isPush(dealer.scoreHands()))
			.count();
	}

	private String findMatchResult(Dealer dealer, Player player) {
		if (player.wins(dealer.scoreHands())) {
			return player.getName() + COLON + WIN;
		}
		if (player.isPush(dealer.scoreHands())) {
			return player.getName() + COLON + DRAW;
		}
		return player.getName() + COLON + DEFEAT;
	}

	public String getGameResult() {
		return gameResult;
	}
}
