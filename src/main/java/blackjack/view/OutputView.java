package blackjack.view;

import blackjack.model.betting.PlayerBettingProfitOutcome;
import blackjack.model.card.Card;
import blackjack.model.dealer.Dealer;
import blackjack.model.dealer.DealerFinalCardsOutcome;
import blackjack.model.player.Player;
import blackjack.model.player.PlayerFinalCardsOutcome;
import blackjack.model.player.Players;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALING_RESULT_INTRO = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String CARDS_FORM = "%s 카드: %s";
    private static final String DEALER_DRAWING_FORM = "\n딜러는 16이하라 1장의 카드를 더 받았습니다.\n";
    private static final String TOTAL_SCORE_FORM = " - 결과: ";
    private static final String BETTING_PROFIT_INTRO = "\n## 최종 수익";
    private static final String DEALER_NAME = "딜러";
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printDealingCards(final Players players, final Dealer dealer) {
        System.out.println(formatDealingResultIntro(players));
        System.out.println(formatCards(List.of(dealer.getFirstCard()), DEALER_NAME));
        for (Player player : players.getPlayers()) {
            System.out.println(formatCards(player.getCards(), player.getName()));
        }
    }

    private String formatDealingResultIntro(final Players players) {
        String names = String.join(", ", players.getNames());
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

    public void printPlayerDrawingCards(final Player player) {
        System.out.println(formatCards(player.getCards(), player.getName()));
    }

    public void printDealerDrawingCards(final Dealer dealer) {
        int count = dealer.getDrawCount();
        System.out.println(DEALER_DRAWING_FORM.repeat(count));
    }

    public void printDealerFinalCards(final DealerFinalCardsOutcome dealerFinalCardsOutcome) {
        List<Card> dealerCards = dealerFinalCardsOutcome.cards();
        int dealerTotalScore = dealerFinalCardsOutcome.totalScore();
        System.out.println(formatFinalCards(dealerCards, DEALER_NAME, dealerTotalScore));
    }

    public void printPlayersFinalCards(final List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes) {
        for (PlayerFinalCardsOutcome playerFinalCardsOutcome : playerFinalCardsOutcomes) {
            List<Card> playerCards = playerFinalCardsOutcome.cards();
            String playerName = playerFinalCardsOutcome.name();
            int playerTotalScore = playerFinalCardsOutcome.totalScore();
            System.out.println(formatFinalCards(playerCards, playerName, playerTotalScore));
        }
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
