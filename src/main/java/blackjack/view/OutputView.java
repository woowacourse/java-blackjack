package blackjack.view;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameResult;
import blackjack.domain.game.MatchResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

public class OutputView {

	private static final String JOIN_DELIMITER = ", ";
	private static final int DEALER_FIRST_CARD_INDEX = 0;

	private OutputView() {

	}

	public static void printInitialCards(List<Player> players, Dealer dealer) {
		System.out.printf("%n%s와 %s에게 2장의 카드를 나누었습니다.%n", dealer.getName(), getPlayerNames(players));
		System.out.printf("%s: %s%n", dealer.getName(), getCardName(getDealerFirstCard(dealer)));
		for (Player player : players) {
			printCards(player);
		}
		System.out.println();
	}

	private static String getPlayerNames(List<Player> players) {
		return players.stream()
			.map(player -> player.getName())
			.collect(Collectors.joining(JOIN_DELIMITER));
	}

	private static String getCardNames(Cards cards) {
		return cards.getValue().stream()
			.map(card -> getCardName(card))
			.collect(Collectors.joining(JOIN_DELIMITER));
	}

	private static String getCardName(Card card) {
		return card.getDenominationName() + card.getSuitName();
	}

	private static Card getDealerFirstCard(Dealer dealer) {
		return dealer.getCards()
			.getValue()
			.get(DEALER_FIRST_CARD_INDEX);
	}

	public static void printCards(Player player) {
		System.out.printf("%s카드: %s%n", player.getName(), getCardNames(player.getCards()));
	}

	public static void printDealerDrawInfo() {
		System.out.printf("%n딜러는 %d이하라 한장의 카드를 더 받았습니다.%n", Dealer.DEALER_FINISHED_SCORE);
	}

	public static void printCardsResult(Dealer dealer, Players players) {
		System.out.println();
		printCardResult(dealer);
		for (Player player : players.getValue()) {
			printCardResult(player);
		}
	}

	private static PrintStream printCardResult(Participant participant) {
		return System.out.printf("%s 카드: %s - 결과: %d%n",
			participant.getName(), getCardNames(participant.getCards()), participant.getCards().sum());
	}

	public static void printGameResult(GameResult gameResult) {
		Map<Player, MatchResult> map = gameResult.getGameResult();

		System.out.println();
		System.out.println("## 최종수익");
		System.out.printf("%s: %.0f%n", Dealer.NAME.getValue(), gameResult.calculateDealerRevenue());
		for (Player player : map.keySet()) {
			System.out.printf("%s: %.0f%n", player.getName(), gameResult.calculatePlayerRevenue(player));
		}
	}
}
