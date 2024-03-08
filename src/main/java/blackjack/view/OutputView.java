package blackjack.view;

import java.util.HashMap;
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
	private static final Map<GameResult, String> gameResultTexts = new HashMap<>();

	static {
		gameResultTexts.put(GameResult.WIN, "승");
		gameResultTexts.put(GameResult.LOSE, "패");
	}

	public static OutputView getInstance() {
		return INSTANCE;
	}

	public void printInitCardStatus(Dealer dealer, Players players) {
		List<Player> playerInfos = players.getPlayers();
		printDealMessage(playerInfos);

		printDealerInitCardHandStatusMessage(dealer);
		printPlayersInitCardHandStatusMessage(playerInfos);
	}

	private void printDealMessage(List<Player> playerInfos) {
		System.out.println(System.lineSeparator() + String.format("딜러와 %s에게 %d장을 나누었습니다.",
			createPlayerNamesText(playerInfos), Dealer.INIT_CARD_COUNT));
	}

	private void printDealerInitCardHandStatusMessage(Dealer dealer) {
		Card dealerInitCard = dealer.getCardHand().get(0);
		System.out.println(String.format("딜러: %s", createCardInfoText(dealerInitCard)));
	}

	private void printPlayersInitCardHandStatusMessage(List<Player> playerInfos) {
		for (Player playerInfo : playerInfos) {
			printCardHandStatus(playerInfo);
		}
		printLine();
	}

	private String createPlayerNamesText(List<Player> playerInfos) {
		StringJoiner playerNameJoiner = new StringJoiner(PLAYER_NAME_DELIMITER);
		for (Player playerInfo : playerInfos) {
			playerNameJoiner.add(playerInfo.getName());
		}

		return playerNameJoiner.toString();
	}

	private String createCardInfoText(Card card) {
		return String.format(CARD_INFO_MESSAGE, card.getRankName(), card.getSuitName());
	}

	public void printCardHandStatus(Player player) {
		System.out.println(
			String.format(PLAYER_CARD_INFO_MESSAGE, player.getName(), createCardsInfoText(player.getCardHand())));
	}

	private String createCardsInfoText(List<Card> cards) {
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

	public void printTotalCardHandStatus(Dealer dealer, Players players) {
		printDealerTotalCardHandStatus(dealer);
		printPlayersTotalCardHandStatus(players);
	}

	private void printDealerTotalCardHandStatus(Dealer dealer) {
		printLine();
		System.out.println(String.format("딜러 카드: %s - 결과: %d",
			createCardsInfoText(dealer.getCardHand()), dealer.getScore()));
	}

	private void printPlayersTotalCardHandStatus(Players players) {
		for (Player player : players.getPlayers()) {
			System.out.println(String.format("%s카드: %s - 결과: %d",
				player.getName(), createCardsInfoText(player.getCardHand()), player.getScore()));
		}
	}

	public void printGameResult(Map<GameResult, Long> dealerResult, Map<Player, GameResult> playerResults) {
		System.out.println(System.lineSeparator() + "## 최종 승패");

		printDealerGameResult(dealerResult);
		printPlayersGameResult(playerResults);
	}

	private void printDealerGameResult(Map<GameResult, Long> dealerResult) {
		System.out.println(
			String.format("딜러: %d승 %d패", dealerResult.get(GameResult.WIN), dealerResult.get(GameResult.LOSE)));
	}

	private void printPlayersGameResult(Map<Player, GameResult> playerResults) {
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
