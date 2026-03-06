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

    public static void printInitialDeal(final Players players, final Dealer dealer) {
        final String playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", playerNames);
        System.out.printf("딜러카드: %s%n", dealer.getCards().get(0).getDisplayName());
        players.getPlayers().forEach(OutputView::printPlayerCards);
        System.out.println();
    }

    public static void printPlayerCards(final Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), formatCards(player.getCards()));
    }

    public static void printDealerHit() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalCards(final Players players, final Dealer dealer) {
        System.out.println();
        printParticipantFinalCards(dealer.getName(), dealer.getCards(), dealer.calculateScore().getValue());
        players.getPlayers().forEach(player ->
                printParticipantFinalCards(player.getName(), player.getCards(), player.calculateScore().getValue())
        );
    }

    public static void printFinalResults(final GameResults gameResults) {
        System.out.println("\n## 최종 승패");
        printDealerResult(gameResults.getDealerResults());
        gameResults.getPlayerResults().forEach(OutputView::printPlayerResult);
    }

    private static void printParticipantFinalCards(final String name, final List<Card> cards, final int score) {
        System.out.printf("%s카드: %s - 결과: %d%n", name, formatCards(cards), score);
    }

    private static void printDealerResult(final Map<GameResult, Integer> dealerResults) {
        System.out.println("딜러: " + buildDealerResultText(dealerResults).trim());
    }

    private static String buildDealerResultText(final Map<GameResult, Integer> dealerResults) {
        final StringBuilder stringBuilder = new StringBuilder();
        appendResultIfExists(stringBuilder, dealerResults, GameResult.WIN, "승 ");
        appendResultIfExists(stringBuilder, dealerResults, GameResult.DRAW, "무 ");
        appendResultIfExists(stringBuilder, dealerResults, GameResult.LOSE, "패");
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
        System.out.printf("%s: %s%n", player.getName(), toDisplayText(result));
    }

    private static String toDisplayText(final GameResult result) {
        if (result == GameResult.WIN) {
            return "승";
        }
        if (result == GameResult.LOSE) {
            return "패";
        }
        return "무";
    }

    private static String formatCards(final List<Card> cards) {
        return cards.stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
    }
}
