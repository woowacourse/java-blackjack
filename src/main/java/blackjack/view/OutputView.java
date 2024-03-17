package blackjack.view;

import blackjack.dto.DealerFinalCardsOutcome;
import blackjack.dto.PlayerBettingProfitOutcome;
import blackjack.dto.PlayerCardsOutcome;
import blackjack.dto.PlayerFinalCardsOutcome;
import blackjack.model.card.Card;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALING_RESULT_INTRO = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String CARDS_FORM = "%s 카드: %s";
    private static final String DEALER_DRAWING_FORM = "\n딜러는 16이하라 1장의 카드를 더 받았습니다.\n";
    private static final String TOTAL_SCORE_FORM = " - 결과: ";
    private static final String BETTING_PROFIT_INTRO = "## 최종 수익";
    private static final String DEALER_NAME = "딜러";
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printDealingCards(final List<String> playerNames,
                                  final List<PlayerCardsOutcome> playerCardsOutcomes,
                                  final Card dealerFirstCard) {
        System.out.println(formatDealingResultIntro(playerNames));
        System.out.println(formatCards(List.of(dealerFirstCard), DEALER_NAME));
        for (PlayerCardsOutcome playerCardsOutcome : playerCardsOutcomes) {
            System.out.println(formatCards(playerCardsOutcome.cards(), playerCardsOutcome.name()));
        }
    }

    private String formatDealingResultIntro(final List<String> playerNames) {
        String names = String.join(", ", playerNames);
        return String.format(DEALING_RESULT_INTRO, names);
    }

    private String formatCards(final List<Card> cards, String name) {
        String joinedCards = cards.stream()
                .map(this::formatCard)
                .collect(Collectors.joining(", "));
        return String.format(CARDS_FORM, name, joinedCards);
    }

    private String formatCard(final Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    public void printPlayerDrawingCards(final PlayerCardsOutcome playerCardsOutcome) {
        System.out.println(formatCards(playerCardsOutcome.cards(), playerCardsOutcome.name()));
    }

    public void printDealerDrawingCards(final int drawCount) {
        System.out.println(DEALER_DRAWING_FORM.repeat(drawCount));
    }

    public void printFinalCards(final DealerFinalCardsOutcome dealerFinalCardsOutcome,
                                final List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes) {
        System.out.println(formatDealerFinalCards(dealerFinalCardsOutcome));
        System.out.println(formatPlayersFinalCards(playerFinalCardsOutcomes));
    }

    public String formatDealerFinalCards(final DealerFinalCardsOutcome dealerFinalCardsOutcome) {
        List<Card> dealerCards = dealerFinalCardsOutcome.cards();
        int dealerTotalScore = dealerFinalCardsOutcome.totalScore();
        return formatFinalCards(dealerCards, DEALER_NAME, dealerTotalScore);
    }

    public String formatPlayersFinalCards(final List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes) {
        StringBuilder sb = new StringBuilder();
        for (PlayerFinalCardsOutcome playerFinalCardsOutcome : playerFinalCardsOutcomes) {
            List<Card> playerCards = playerFinalCardsOutcome.cards();
            String playerName = playerFinalCardsOutcome.name();
            int playerTotalScore = playerFinalCardsOutcome.totalScore();
            sb.append(formatFinalCards(playerCards, playerName, playerTotalScore)).append("\n");
        }
        return sb.toString();
    }

    private String formatFinalCards(final List<Card> cards, final String name, final int totalScore) {
        return formatCards(cards, name) + formatTotalScore(totalScore);
    }

    private String formatTotalScore(final int totalScore) {
        return TOTAL_SCORE_FORM + totalScore;
    }

    public void printBettingProfit(final int dealerBettingProfit,
                                   final List<PlayerBettingProfitOutcome> playerBettingProfitOutcomes) {
        System.out.println(BETTING_PROFIT_INTRO);
        System.out.println(formatDealerBettingProfit(dealerBettingProfit));
        System.out.println(formatPlayerBettingProfits(playerBettingProfitOutcomes));
    }

    private String formatDealerBettingProfit(final int dealerBettingProfit) {
        return formatBettingProfit(DEALER_NAME, dealerBettingProfit);
    }

    private String formatPlayerBettingProfits(final List<PlayerBettingProfitOutcome> playerBettingProfitOutcomes) {
        return playerBettingProfitOutcomes.stream()
                .map(this::formatPlayerBettingProfit)
                .collect(Collectors.joining("\n"));
    }

    private String formatPlayerBettingProfit(final PlayerBettingProfitOutcome playerBettingProfitOutcome) {
        return formatBettingProfit(playerBettingProfitOutcome.name(), playerBettingProfitOutcome.profit());
    }

    private String formatBettingProfit(final String name, final int profit) {
        return name + ": " + profit;
    }

    public void printException(final String errorMessage) {
        System.out.println(ERROR_MESSAGE_PREFIX + errorMessage);
    }
}
