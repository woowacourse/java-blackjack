package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.GameResults;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public void printInitialDeal(final Players players, final Dealer dealer) {
        final String playerNames = players.players().stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", playerNames);
        System.out.printf("딜러카드: %s%n", dealer.getCards().get(0).getDisplayName());
        players.players().forEach(this::printPlayerCards);
        System.out.println();
    }

    public void printPlayerCards(final Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), formatCards(player.getCards()));
    }

    public void printDealerHit() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCards(final Players players, final Dealer dealer) {
        System.out.println();
        printParticipantFinalCards(dealer.getName(), dealer.getCards(), dealer.calculateScore().value());
        players.players().forEach(player ->
                printParticipantFinalCards(player.getName(), player.getCards(), player.calculateScore().value())
        );
    }

    public void printFinalResults(final GameResults gameResults) {
        System.out.println("\n## 최종 승패");
        printDealerResult(gameResults.dealerResult());
        gameResults.playerResults().forEach(this::printPlayerResult);
    }

    private void printParticipantFinalCards(final String name, final List<Card> cards, final int score) {
        System.out.printf("%s카드: %s - 결과: %d%n", name, formatCards(cards), score);
    }

    private void printDealerResult(Map<GameResult, Integer> dealerResults) {
        System.out.println("딜러: " + buildDealerResultText(dealerResults).trim());
    }

    private String buildDealerResultText(final Map<GameResult, Integer> dealerResults) {
        final StringBuilder stringBuilder = new StringBuilder();
        appendResultIfExists(stringBuilder, dealerResults, GameResult.WIN, "승 ");
        appendResultIfExists(stringBuilder, dealerResults, GameResult.DRAW, "무 ");
        appendResultIfExists(stringBuilder, dealerResults, GameResult.LOSE, "패");
        return stringBuilder.toString();
    }

    private void appendResultIfExists(
            final StringBuilder stringBuilder,
            final Map<GameResult, Integer> dealerResults,
            final GameResult result,
            final String label
    ) {
        if (dealerResults.containsKey(result)) {
            stringBuilder.append(dealerResults.get(result)).append(label);
        }
    }

    private void printPlayerResult(final Participant player, final GameResult result) {
        System.out.printf("%s: %s%n", player.getName(), toDisplayText(result));
    }

    private String toDisplayText(final GameResult result) {
        if (result == GameResult.WIN) {
            return "승";
        }
        if (result == GameResult.LOSE) {
            return "패";
        }
        return "무";
    }

    private String formatCards(final List<Card> cards) {
        return cards.stream()
                .map(Card::getDisplayName)
                .collect(Collectors.joining(", "));
    }
}
