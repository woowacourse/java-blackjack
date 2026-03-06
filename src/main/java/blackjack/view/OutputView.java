package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.GameResults;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String INITIAL_DEAL_MESSAGE = "%n딜러와 %s에게 2장을 나누었습니다.%n";
    private static final String DEALER_CARD_MESSAGE = "딜러카드: %s%n";
    private static final String PLAYER_CARD_MESSAGE = "%s카드: %s%n";
    private static final String DEALER_HIT_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_CARDS_FORMAT = "%s카드: %s - 결과: %d%n";
    private static final String FINAL_RESULTS_HEADER = "\n## 최종 승패";
    private static final String DEALER_RESULT_PREFIX = "딜러: ";
    private static final String PLAYER_RESULT_FORMAT = "%s: %s%n";
    private static final String DELIMITER = ", ";
    private static final String RESULT_LABEL_SUFFIX = " ";
    private static final String WIN = "승";
    private static final String DRAW = "무";
    private static final String LOSE = "패";

    public static void printInitialDeal(final Players players, final Dealer dealer) {
        final String playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));
        System.out.printf(INITIAL_DEAL_MESSAGE, playerNames);
        System.out.printf(DEALER_CARD_MESSAGE, dealer.getCards().get(0).getDisplayName());
        players.getPlayers().forEach(OutputView::printPlayerCards);
        System.out.println();
    }

    public static void printPlayerCards(final Player player) {
        System.out.printf(PLAYER_CARD_MESSAGE, player.getName(), formatCards(player.getCards()));
    }

    public static void printDealerHit() {
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public static void printFinalCards(final Players players, final Dealer dealer) {
        System.out.println();
        printParticipantFinalCards(dealer.getName(), dealer.getCards(), dealer.calculateScore().getValue());
        players.getPlayers().forEach(player ->
                printParticipantFinalCards(player.getName(), player.getCards(), player.calculateScore().getValue())
        );
    }

    public static void printFinalResults(final GameResults gameResults) {
        System.out.println(FINAL_RESULTS_HEADER);
        printDealerResult(gameResults.getDealerResults());
        gameResults.getPlayerResults().forEach(OutputView::printPlayerResult);
    }

    private static void printParticipantFinalCards(final String name, final List<Card> cards, final int score) {
        System.out.printf(FINAL_CARDS_FORMAT, name, formatCards(cards), score);
    }

    private static void printDealerResult(final Map<GameResult, Integer> dealerResults) {
        System.out.println(DEALER_RESULT_PREFIX + buildDealerResultText(dealerResults).trim());
    }

    private static String buildDealerResultText(final Map<GameResult, Integer> dealerResults) {
        final StringBuilder stringBuilder = new StringBuilder();
        appendResultIfExists(stringBuilder, dealerResults, GameResult.WIN, WIN + RESULT_LABEL_SUFFIX);
        appendResultIfExists(stringBuilder, dealerResults, GameResult.DRAW, DRAW + RESULT_LABEL_SUFFIX);
        appendResultIfExists(stringBuilder, dealerResults, GameResult.LOSE, LOSE);
        return stringBuilder.toString();
    }

    private static void appendResultIfExists(
            final StringBuilder stringBuilder,
            final Map<GameResult, Integer> dealerResults,
            final GameResult result,
            final String label
    ) {
        if (dealerResults.containsKey(result)) {
            stringBuilder.append(dealerResults.get(result)).append(label);
        }
    }

    private static void printPlayerResult(final Player player, final GameResult result) {
        System.out.printf(PLAYER_RESULT_FORMAT, player.getName(), toDisplayText(result));
    }

    private static String toDisplayText(final GameResult result) {
        if (result == GameResult.WIN) {
            return WIN;
        }
        if (result == GameResult.LOSE) {
            return LOSE;
        }
        return DRAW;
    }

    private static String formatCards(final List<Card> cards) {
        return cards.stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(DELIMITER));
    }
}
