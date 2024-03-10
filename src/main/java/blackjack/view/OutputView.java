package blackjack.view;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

public class OutputView {
	private static final OutputView INSTANCE = new OutputView();

	private static final String CARD_INFO_DELIMITER = ", ";
	private static final String PLAYER_NAME_DELIMITER = ", ";
	private static final String CARD_INFO_MESSAGE = "%s%s";
	private static final String PLAYER_CARD_INFO_MESSAGE = "%s카드: %s";
	private static final String PLAYER_GAME_RESULT_MESSAGE = "%s: %s";
	private static final Map<GameResult, String> gameResultTexts = new EnumMap<>(GameResult.class);

	static {
		gameResultTexts.put(GameResult.WIN, "승");
		gameResultTexts.put(GameResult.LOSE, "패");
	}

	public static OutputView getInstance() {
		return INSTANCE;
	}

	public void printInitCardStatus(final Dealer dealer, final Players players) {
		List<Player> playerInfos = players.getPlayers();
		printDealMessage(playerInfos);

		printDealerInitCardHandStatusMessage(dealer);
		printPlayersInitCardHandStatusMessage(playerInfos);
	}

	private void printDealMessage(final List<Player> playerInfos) {
		System.out.println(System.lineSeparator() + String.format("딜러와 %s에게 %d장을 나누었습니다.",
			createPlayerNamesText(playerInfos), Dealer.INIT_CARD_COUNT));
	}

	private void printDealerInitCardHandStatusMessage(final Dealer dealer) {
		Card dealerInitCard = dealer.getCardHand().get(0);
		System.out.println(String.format("딜러: %s", createCardInfoText(dealerInitCard)));
	}

	private void printPlayersInitCardHandStatusMessage(final List<Player> playerInfos) {
		for (Player playerInfo : playerInfos) {
			printCardHandStatus(playerInfo);
		}
		printLine();
	}

	private String createPlayerNamesText(final List<Player> playerInfos) {
		StringJoiner playerNameJoiner = new StringJoiner(PLAYER_NAME_DELIMITER);
		for (Player playerInfo : playerInfos) {
			playerNameJoiner.add(playerInfo.getName());
		}

		return playerNameJoiner.toString();
	}

	private String createCardInfoText(final Card card) {
		return String.format(CARD_INFO_MESSAGE, card.getRankName(), card.getSuitName());
	}

	public void printCardHandStatus(final Player player) {
		System.out.println(
			String.format(PLAYER_CARD_INFO_MESSAGE, player.getName(), createCardsInfoText(player.getCardHand())));
	}

	private String createCardsInfoText(final List<Card> cards) {
		StringJoiner cardInfoJoiner = new StringJoiner(CARD_INFO_DELIMITER);
		for (Card card : cards) {
			cardInfoJoiner.add(createCardInfoText(card));
		}

		return cardInfoJoiner.toString();
	}

	public void printDealerHitMessage() {
		System.out.println(System.lineSeparator() +
			String.format("딜러는 %d이하라 한장의 카드를 더 받았습니다.", Dealer.MAX_HIT_SCORE));
	}

	public void printTotalCardHandStatus(final Dealer dealer, final Players players) {
		printDealerTotalCardHandStatus(dealer);
		printPlayersTotalCardHandStatus(players);
	}

	private void printDealerTotalCardHandStatus(final Dealer dealer) {
		printLine();
		System.out.println(String.format("딜러 카드: %s - 결과: %d",
			createCardsInfoText(dealer.getCardHand()), dealer.getScore()));
	}

	private void printPlayersTotalCardHandStatus(final Players players) {
		for (Player player : players.getPlayers()) {
			System.out.println(String.format("%s카드: %s - 결과: %d",
				player.getName(), createCardsInfoText(player.getCardHand()), player.getScore()));
		}
	}

	public void printGameResult(final Map<GameResult, Long> dealerResult, final Map<Player, GameResult> playerResults) {
		System.out.println(System.lineSeparator() + "## 최종 승패");

		printDealerGameResult(dealerResult);
		printPlayersGameResult(playerResults);
	}

	private void printDealerGameResult(final Map<GameResult, Long> dealerResult) {
		System.out.println(
			String.format("딜러: %d승 %d패", dealerResult.getOrDefault(GameResult.WIN, 0L),
				dealerResult.getOrDefault(GameResult.LOSE, 0L)));
	}

	private void printPlayersGameResult(final Map<Player, GameResult> playerResults) {
		for (Map.Entry<Player, GameResult> playerToResult : playerResults.entrySet()) {
			String playerName = playerToResult.getKey().getName();
			String result = gameResultTexts.get(playerToResult.getValue());
			System.out.println(String.format(PLAYER_GAME_RESULT_MESSAGE, playerName, result));
		}
	}

	private void printLine() {
		System.out.println();
	}
}
