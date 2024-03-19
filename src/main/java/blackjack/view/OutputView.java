package blackjack.view;

import blackjack.dto.DealerFinalCardsOutcome;
import blackjack.dto.PlayerBettingProfitOutcome;
import blackjack.dto.PlayerCardsOutcome;
import blackjack.dto.PlayerFinalCardsOutcome;
import blackjack.vo.Card;
import blackjack.vo.PlayerName;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALING_RESULT_INTRO_FORM = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final PlayerName DEALER_NAME = new PlayerName("딜러");
    private static final String CARDS_WITH_NAME_FORM = "%s 카드: %s";
    private static final String DEALER_DRAWING_CARDS_FORM = "\n딜러는 16이하라 1장의 카드를 더 받았습니다.";
    private static final String SCORE_FORM = " - 결과: %s";
    private static final String BETTING_PROFIT_INTRO = "\n## 최종 수익";
    private static final String BETTING_PROFIT_FORM = "%s: %d";
    private static final String ERROR_MESSAGE_FORM = "[ERROR] %s\n";

    public void printDealingCards(final List<PlayerName> playerNames,
                                  final List<PlayerCardsOutcome> playerCardsOutcomes,
                                  final Card dealerFirstCard) {
        System.out.println(formatDealingResultIntro(playerNames));
        System.out.println(formatCardsWithName(List.of(dealerFirstCard), DEALER_NAME));
        for (PlayerCardsOutcome playerCardsOutcome : playerCardsOutcomes) {
            System.out.println(formatCardsWithName(playerCardsOutcome.cards(), playerCardsOutcome.name()));
        }
    }

    private String formatDealingResultIntro(final List<PlayerName> playerNames) {
        String names = playerNames.stream()
                .map(PlayerName::name)
                .collect(Collectors.joining(", "));
        return String.format(DEALING_RESULT_INTRO_FORM, names);
    }

    private String formatCardsWithName(final List<Card> cards, PlayerName name) {
        String joinedCards = cards.stream()
                .map(this::formatCard)
                .collect(Collectors.joining(", "));
        return String.format(CARDS_WITH_NAME_FORM, name, joinedCards);
    }

    private String formatCard(final Card card) {
        return card.denomination().getName() + card.suit().getName();
    }

    public void printPlayerDrawingCards(final PlayerCardsOutcome playerCardsOutcome) {
        System.out.println(formatCardsWithName(playerCardsOutcome.cards(), playerCardsOutcome.name()));
    }

    public void printDealerDrawingCards(final int drawCount) {
        System.out.println(formatDealerDrawingCards(drawCount));
    }

    private String formatDealerDrawingCards(final int drawCount) {
        return DEALER_DRAWING_CARDS_FORM.repeat(drawCount) + System.lineSeparator();
    }

    public void printFinalCards(final DealerFinalCardsOutcome dealerFinalCardsOutcome,
                                final List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes) {
        System.out.println(formatDealerFinalCards(dealerFinalCardsOutcome));
        System.out.println(formatPlayersFinalCards(playerFinalCardsOutcomes));
    }

    private String formatPlayersFinalCards(final List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes) {
        return playerFinalCardsOutcomes.stream()
                .map(outcome -> formatFinalCards(outcome.cards(), outcome.name(), outcome.score()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String formatDealerFinalCards(final DealerFinalCardsOutcome dealerFinalCardsOutcome) {
        List<Card> dealerCards = dealerFinalCardsOutcome.cards();
        int dealerScore = dealerFinalCardsOutcome.score();
        return formatFinalCards(dealerCards, DEALER_NAME, dealerScore);
    }

    private String formatFinalCards(final List<Card> cards, final PlayerName name, final int score) {
        return formatCardsWithName(cards, name) + formatScore(score);
    }

    private String formatScore(final int score) {
        return String.format(SCORE_FORM, score);
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

    public String formatPlayerBettingProfits(
            final List<PlayerBettingProfitOutcome> playerBettingProfitOutcomes) {
        return playerBettingProfitOutcomes.stream()
                .map(this::formatPlayerBettingProfit)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String formatPlayerBettingProfit(final PlayerBettingProfitOutcome playerBettingProfitOutcome) {
        return formatBettingProfit(playerBettingProfitOutcome.name(), playerBettingProfitOutcome.profit());
    }

    private String formatBettingProfit(final PlayerName name, final int profit) {
        return String.format(BETTING_PROFIT_FORM, name, profit);
    }

    public void printException(final String errorMessage) {
        System.out.printf(ERROR_MESSAGE_FORM, errorMessage);
    }
}
