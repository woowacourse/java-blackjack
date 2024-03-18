package blackjack.view.form;

import blackjack.dto.DealerFinalCardsOutcome;
import blackjack.dto.PlayerBettingProfitOutcome;
import blackjack.dto.PlayerFinalCardsOutcome;
import blackjack.model.card.Card;
import blackjack.model.player.PlayerName;
import java.util.List;
import java.util.stream.Collectors;

public class OutputFormatter {
    private static final String DEALING_RESULT_INTRO_FORM = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String CARDS_WITH_NAME_FORM = "%s 카드: %s";
    private static final String DEALER_DRAWING_CARDS_FORM = "\n딜러는 16이하라 1장의 카드를 더 받았습니다.";
    private static final String SCORE_FORM = " - 결과: %s";
    private static final String BETTING_PROFIT_FORM = "%s: %d";
    private static final String ERROR_MESSAGE_FORM = "[ERROR] %s";

    public static String formatDealingResultIntro(final List<PlayerName> playerNames) {
        String names = playerNames.stream()
                .map(PlayerName::name)
                .collect(Collectors.joining(", "));
        return String.format(DEALING_RESULT_INTRO_FORM, names);
    }

    public static String formatCardsWithName(final List<Card> cards, PlayerName name) {
        String joinedCards = cards.stream()
                .map(OutputFormatter::formatCard)
                .collect(Collectors.joining(", "));
        return String.format(CARDS_WITH_NAME_FORM, name, joinedCards);
    }

    private static String formatCard(final Card card) {
        return card.denomination().getName() + card.suit().getName();
    }

    public static String formatDealerDrawingCards(final int drawCount) {
        return DEALER_DRAWING_CARDS_FORM.repeat(drawCount) + System.lineSeparator();
    }

    public static String formatDealerFinalCards(final DealerFinalCardsOutcome dealerFinalCardsOutcome,
                                                final PlayerName dealerName) {
        List<Card> dealerCards = dealerFinalCardsOutcome.cards();
        int dealerScore = dealerFinalCardsOutcome.score();
        return formatFinalCards(dealerCards, dealerName, dealerScore);
    }

    public static String formatPlayersFinalCards(final List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes) {
        return playerFinalCardsOutcomes.stream()
                .map(outcome -> formatFinalCards(outcome.cards(), outcome.name(), outcome.score()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private static String formatFinalCards(final List<Card> cards, final PlayerName name, final int score) {
        return formatCardsWithName(cards, name) + formatScore(score);
    }

    private static String formatScore(final int score) {
        return String.format(SCORE_FORM, score);
    }

    public static String formatDealerBettingProfit(final List<PlayerBettingProfitOutcome> playerBettingProfitOutcomes,
                                                   final PlayerName dealerName) {
        int dealerBettingProfit = calculateDealerBettingProfit(playerBettingProfitOutcomes);
        return formatBettingProfit(dealerName, dealerBettingProfit);
    }

    private static int calculateDealerBettingProfit(
            final List<PlayerBettingProfitOutcome> playerBettingProfitOutcomes) {
        return playerBettingProfitOutcomes.stream()
                .mapToInt(PlayerBettingProfitOutcome::profit)
                .sum() * -1;
    }

    public static String formatPlayerBettingProfits(
            final List<PlayerBettingProfitOutcome> playerBettingProfitOutcomes) {
        return playerBettingProfitOutcomes.stream()
                .map(OutputFormatter::formatPlayerBettingProfit)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private static String formatPlayerBettingProfit(final PlayerBettingProfitOutcome playerBettingProfitOutcome) {
        return formatBettingProfit(playerBettingProfitOutcome.name(), playerBettingProfitOutcome.profit());
    }

    private static String formatBettingProfit(final PlayerName name, final int profit) {
        return String.format(BETTING_PROFIT_FORM, name, profit);
    }

    public static String formatErrorMessage(final String errorMessage) {
        return String.format(ERROR_MESSAGE_FORM, errorMessage);
    }
}
