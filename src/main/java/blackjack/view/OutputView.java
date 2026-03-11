package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.money.Money;
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

    private static final String JOIN_DELIMITER = ", ";
    private static final String INITIAL_DEAL_FORMAT = "%n딜러와 %s에게 2장을 나누었습니다.%n";
    private static final String DEALER_FIRST_CARD_FORMAT = "딜러카드: %s%n";
    private static final String PLAYER_CARDS_FORMAT = "%s카드: %s%n";
    private static final String DEALER_HIT_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PARTICIPANT_FINAL_CARDS_FORMAT = "%s카드: %s - 결과: %d%n";
    private static final String FINAL_RESULTS_HEADER = "\n## 최종 승패";
    private static final String DEALER_RESULT_FORMAT = "딜러: %s%n";
    private static final String PLAYER_RESULT_FORMAT = "%s: %s%n";
    private static final String WIN_DISPLAY_TEXT = "승";
    private static final String LOSE_DISPLAY_TEXT = "패";
    private static final String DRAW_DISPLAY_TEXT = "무";
    private static final String WIN_LABEL = "승 ";
    private static final String DRAW_LABEL = "무 ";
    private static final String LOSE_LABEL = "패";
    private static final String FINAL_PROFITS_HEADER = "\n## 최종 수익";

    public void printInitialDeal(final Players players, final Dealer dealer) {
        final String playerNames = players.players().stream()
                .map(Player::getName)
                .collect(Collectors.joining(JOIN_DELIMITER));
        System.out.printf(INITIAL_DEAL_FORMAT, playerNames);
        System.out.printf(DEALER_FIRST_CARD_FORMAT, dealer.getFirstCard());
        players.players().forEach(this::printPlayerCards);
        System.out.println();
    }

    public void printPlayerCards(final Player player) {
        System.out.printf(PLAYER_CARDS_FORMAT, player.getName(), formatCards(player.getCards()));
    }

    public void printDealerHit() {
        System.out.println(DEALER_HIT_MESSAGE);
    }

    public void printFinalCards(final Players players, final Dealer dealer) {
        System.out.println();
        printParticipantFinalCards(dealer.getName(), dealer.getCards(), dealer.calculateScore().value());
        players.players().forEach(player ->
                printParticipantFinalCards(player.getName(), player.getCards(), player.calculateScore().value())
        );
    }

    public void printFinalResults(final GameResults gameResults) {
        System.out.println(FINAL_RESULTS_HEADER);
        printDealerResult(gameResults.dealerResult());
        gameResults.playerResults().forEach(this::printPlayerResult);
    }

    public void printProfits(Map<Participant, Money> profits) {
        System.out.println(FINAL_PROFITS_HEADER);
        for (Participant participant : profits.keySet()) {
            System.out.printf(PLAYER_RESULT_FORMAT, participant.getName(), profits.get(participant).value());
        }
    }

    private void printParticipantFinalCards(final String name, final List<Card> cards, final int score) {
        System.out.printf(PARTICIPANT_FINAL_CARDS_FORMAT, name, formatCards(cards), score);
    }

    private void printDealerResult(Map<GameResult, Integer> dealerResults) {
        System.out.printf(DEALER_RESULT_FORMAT, buildDealerResultText(dealerResults).trim());
    }

    private String buildDealerResultText(final Map<GameResult, Integer> dealerResults) {
        final StringBuilder stringBuilder = new StringBuilder();
        appendResultIfExists(stringBuilder, dealerResults, GameResult.WIN, WIN_LABEL);
        appendResultIfExists(stringBuilder, dealerResults, GameResult.DRAW, DRAW_LABEL);
        appendResultIfExists(stringBuilder, dealerResults, GameResult.LOSE, LOSE_LABEL);
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
        System.out.printf(PLAYER_RESULT_FORMAT, player.getName(), toDisplayText(result));
    }

    private String toDisplayText(final GameResult result) {
        if (result == GameResult.WIN || result == GameResult.BLACKJACK) {
            return WIN_DISPLAY_TEXT;
        }
        if (result == GameResult.LOSE) {
            return LOSE_DISPLAY_TEXT;
        }
        return DRAW_DISPLAY_TEXT;
    }

    private String formatCards(final List<Card> cards) {
        return cards.stream()
                .map(Card::toDisplayName)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }
}
