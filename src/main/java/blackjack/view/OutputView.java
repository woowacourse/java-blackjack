package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.model.referee.Outcome;
import blackjack.view.dto.DealerFinalCardsOutcome;
import blackjack.view.dto.PlayerFinalCardsOutcome;
import blackjack.view.dto.PlayerOutcome;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALING_RESULT_INTRO = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String PLAYER_CARDS_FORM = "%s카드: %s";
    private static final String DEALER_ACTION_FORM = "\n딜러는 16이하라 %d장의 카드를 더 받았습니다.\n";
    private static final String DEALER_CARDS_FORM = "\n딜러 카드: %s";
    private static final String TOTAL_SCORE_FORM = " - 결과: %s\n";
    private static final String FINAL_OUTCOME_INTRO = "\n## 최종 승패";
    public static final String DEALER_FINAL_OUTCOME_PREFIX = "딜러: ";
    public static final String WIN_MESSAGE = "승";
    public static final String LOSE_MESSAGE = "패";
    public static final String TIE_MESSAGE = "무";
    public static final String DEALER_FINAL_OUTCOME_FORM = "%s승 %s패 %s무";

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
        List<Card> cards = player.getCards().getCards();
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

    public void printFinalOutcome(final List<PlayerOutcome> playerOutcomes) {
        System.out.println(FINAL_OUTCOME_INTRO);
        System.out.println(DEALER_FINAL_OUTCOME_PREFIX + formatDealerOutcome(playerOutcomes));
        for (PlayerOutcome playerOutcome : playerOutcomes) {
            System.out.println(playerOutcome.name() + ": " + formatPlayerOutcome(playerOutcome.outcome()));
        }
    }

    private String formatPlayerOutcome(final Outcome outcome) {
        if (outcome == Outcome.WIN) {
            return WIN_MESSAGE;
        }
        if (outcome == Outcome.LOSE) {
            return LOSE_MESSAGE;
        }
        return TIE_MESSAGE;
    }

    private String formatDealerOutcome(final List<PlayerOutcome> playerOutcomes) {
        Map<Outcome, Long> outcomeCounts = playerOutcomes.stream()
                .collect(Collectors.groupingBy(PlayerOutcome::outcome, Collectors.counting()));
        long winCount = outcomeCounts.getOrDefault(Outcome.LOSE, 0L);
        long loseCount = outcomeCounts.getOrDefault(Outcome.WIN, 0L);
        long tieCount = outcomeCounts.getOrDefault(Outcome.TIE, 0L);
        return String.format(DEALER_FINAL_OUTCOME_FORM, winCount, loseCount, tieCount);
    }
}
