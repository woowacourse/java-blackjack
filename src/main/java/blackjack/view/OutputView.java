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
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALING_RESULT_INTRO = "\n딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_DEALING_FORM = "딜러: %s";
    private static final String PLAYER_CARDS_FORM = "%s카드: %s";
    private static final String DEALER_ACTION_FORM = "\n딜러는 16이하라 %d장의 카드를 더 받았습니다.";
    private static final String DEALER_CARDS_FORM = "\n딜러 카드: %s";
    private static final String TOTAL_SCORE_FORM = " - 결과: %s";
    private static final String FINAL_OUTCOME_INTRO = "\n## 최종 승패";

    public void printDealingResult(final Players players, final Dealer dealer) {
        String names = String.join(", ", players.getNames());
        System.out.println(String.format(DEALING_RESULT_INTRO, names));
        System.out.println(String.format(DEALER_DEALING_FORM, formatCard(dealer.getFirstCard())));
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
        System.out.println(String.format(DEALER_ACTION_FORM, dealer.getActionCount()));
    }

    public void printFinalCards(
            final DealerFinalCardsOutcome dealerFinalCardsOutcome,
            final List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes) {
        List<Card> dealerCards = dealerFinalCardsOutcome.cards();
        int dealerTotalScore = dealerFinalCardsOutcome.totalScore();
        System.out.println(String.format(DEALER_CARDS_FORM, formatCards(dealerCards))
                + String.format(TOTAL_SCORE_FORM, dealerTotalScore));

        for (PlayerFinalCardsOutcome playerOutcome : playerFinalCardsOutcomes) {
            String playerName = playerOutcome.name();
            List<Card> playerCards = playerOutcome.cards();
            int playerTotalScore = playerOutcome.totalScore();
            System.out.println(String.format(PLAYER_CARDS_FORM, playerName, formatCards(playerCards))
                    + String.format(TOTAL_SCORE_FORM, playerTotalScore));
        }
    }

    public void printFinalOutcome(final List<PlayerOutcome> playerOutcomes) {
        System.out.println(FINAL_OUTCOME_INTRO);

        System.out.println("딜러: " + formatDealerOutcome(playerOutcomes));
        for (PlayerOutcome playerOutcome : playerOutcomes) {
            System.out.println(playerOutcome.name() + ": " + formatPlayerOutcome(playerOutcome.outcome()));
        }
    }

    private String formatPlayerOutcome(final Outcome outcome) {
        if (outcome == Outcome.WIN) {
            return "승";
        }
        if (outcome == Outcome.LOSE) {
            return "패";
        }
        return "무";
    }

    private String formatDealerOutcome(final List<PlayerOutcome> playerOutcomes) {
        int winCount = 0;
        int loseCount = 0;
        for (PlayerOutcome playerOutcome : playerOutcomes) {
            if (playerOutcome.outcome() == Outcome.WIN) {
                loseCount++;
            } else if (playerOutcome.outcome() == Outcome.LOSE) {
                winCount++;
            }
        }
        int tieCount = playerOutcomes.size() - winCount - loseCount;
        return String.format("%s승 %s패 %s무", winCount, loseCount, tieCount);
    }
}
