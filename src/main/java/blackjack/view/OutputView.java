package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.view.dto.DealerFinalCardsOutcome;
import blackjack.view.dto.PlayerFinalCardsOutcome;
import blackjack.view.dto.PlayerProfit;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALING_RESULT_INTRO = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String PLAYER_CARDS_FORM = "%s카드: %s";
    private static final String DEALER_ACTION_FORM = "\n딜러는 16이하라 %d장의 카드를 더 받았습니다.\n";
    private static final String DEALER_CARDS_FORM = "\n딜러 카드: %s";
    private static final String TOTAL_SCORE_FORM = " - 결과: %s\n";
    private static final String FINAL_PROFIT_INTRO = "\n## 최종 수익";
    public static final String DEALER_PROFIT_PREFIX = "딜러: ";
    public static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printDealingResult(final Players players, final Dealer dealer) {
        String names = String.join(", ", players.getNames());
        System.out.printf(DEALING_RESULT_INTRO, names);
        System.out.printf(DEALER_CARDS_FORM, formatCard(dealer.getFirstCard()));
        System.out.println();
        for (Player player : players.getPlayers()) {
            System.out.println(formatPlayerCards(player));
        }
    }

    private String formatCard(final Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    private String formatPlayerCards(final Player player) {
        List<Card> cards = player.getCards();
        String joinedCards = formatCards(cards);
        return String.format(PLAYER_CARDS_FORM, player.getName(), joinedCards);
    }

    private String formatCards(final List<Card> cards) {
        return cards.stream()
                .map(this::formatCard)
                .collect(Collectors.joining(", "));
    }

    public void printPlayerActionResult(final Player player) {
        System.out.println(formatPlayerCards(player));
    }

    public void printDealerActionResult(final Dealer dealer) {
        System.out.printf(DEALER_ACTION_FORM, dealer.getActionCount());
    }

    public void printDealerFinalCards(final DealerFinalCardsOutcome dealerFinalCardsOutcome) {
        List<Card> dealerCards = dealerFinalCardsOutcome.cards();
        int dealerTotalScore = dealerFinalCardsOutcome.totalScore();
        System.out.printf(DEALER_CARDS_FORM, formatCards(dealerCards));
        System.out.printf(TOTAL_SCORE_FORM, dealerTotalScore);
    }

    public void printPlayersFinalCards(final List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes) {
        for (PlayerFinalCardsOutcome playerOutcome : playerFinalCardsOutcomes) {
            String playerName = playerOutcome.name();
            List<Card> playerCards = playerOutcome.cards();
            int playerTotalScore = playerOutcome.totalScore();
            System.out.printf(PLAYER_CARDS_FORM, playerName, formatCards(playerCards));
            System.out.printf(TOTAL_SCORE_FORM, playerTotalScore);
        }
    }

    public void printFinalProfits(final List<PlayerProfit> playerProfits) {
        System.out.println(FINAL_PROFIT_INTRO);
        System.out.println(formatDealerProfit(playerProfits));
        for (PlayerProfit playerProfit : playerProfits) {
            System.out.println(formatPlayerProfit(playerProfit));
        }
    }

    private String formatDealerProfit(final List<PlayerProfit> playerProfits) {
        double profit = (-1) * playerProfits.stream()
                .mapToDouble(PlayerProfit::profit)
                .sum();
        return DEALER_PROFIT_PREFIX + profit;
    }

    private String formatPlayerProfit(final PlayerProfit playerProfit) {
        return playerProfit.name() + ": " + playerProfit.profit();
    }

    public void printException(final String errorMessage) {
        System.out.println(ERROR_MESSAGE_PREFIX + errorMessage);
    }
}
