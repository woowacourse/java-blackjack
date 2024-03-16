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
    private static final String PLAYER_CARDS_FORM = "%s카드: %s";
    private static final String DEALER_DRAWING_FORM = "\n딜러는 16이하라 1장의 카드를 더 받았습니다.";
    private static final String DEALER_CARDS_FORM = "\n딜러카드: %s";
    private static final String TOTAL_SCORE_FORM = " - 결과: %s\n";
    private static final String BETTING_PROFIT_INTRO = "\n## 최종 수익";
    private static final String DEALER_BETTING_PROFIT_PREFIX = "딜러: ";
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printDealingCards(final Players players, final Dealer dealer) {
        String names = String.join(", ", players.getNames());
        System.out.printf(DEALING_RESULT_INTRO, names);
        System.out.printf(DEALER_CARDS_FORM, formatCard(dealer.getFirstCard()));
        System.out.println();
        for (Player player : players.getPlayers()) {
            System.out.println(formatPlayerCards(player));
        }
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

    private String formatCard(final Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    public void printPlayerDrawingCards(final Player player) {
        System.out.println(formatPlayerCards(player));
    }

    public void printDealerDrawingCards(final Dealer dealer) {
        int count = dealer.getDrawCount();
        System.out.println(DEALER_DRAWING_FORM.repeat(count));
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

    public void printBettingProfit(final int dealerBettingProfit,
                                   final List<PlayerBettingProfitOutcome> playerBettingProfitOutcomes) {
        System.out.println(BETTING_PROFIT_INTRO);
        System.out.println(formatDealerBettingProfit(dealerBettingProfit));
        System.out.println(formatPlayerBettingProfits(playerBettingProfitOutcomes));
    }

    private String formatDealerBettingProfit(final int dealerBettingProfit) {
        return DEALER_BETTING_PROFIT_PREFIX + dealerBettingProfit;
    }

    private String formatPlayerBettingProfits(final List<PlayerBettingProfitOutcome> playerBettingProfitOutcomes) {
        return playerBettingProfitOutcomes.stream()
                .map(this::formatPlayerBettingProfit)
                .collect(Collectors.joining("\n"));
    }

    private String formatPlayerBettingProfit(final PlayerBettingProfitOutcome playerBettingProfitOutcome) {
        return playerBettingProfitOutcome.name() + ": " + playerBettingProfitOutcome.profit();
    }

    public void printException(final String errorMessage) {
        System.out.println(ERROR_MESSAGE_PREFIX + errorMessage);
    }
}
