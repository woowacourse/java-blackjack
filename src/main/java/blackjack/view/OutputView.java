package blackjack.view;

import blackjack.model.card.Card;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.MatchResult;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.view.dto.BettingProfitOutcome;
import blackjack.view.dto.DealerFinalCardsOutcome;
import blackjack.view.dto.PlayerFinalCardsOutcome;
import blackjack.view.dto.PlayerMatchResultOutcome;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALING_RESULT_INTRO = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String PLAYER_CARDS_FORM = "%s카드: %s";
    private static final String DEALER_DRAWING_FORM = "\n딜러는 16이하라 1장의 카드를 더 받았습니다.";
    private static final String DEALER_CARDS_FORM = "\n딜러카드: %s";
    private static final String TOTAL_SCORE_FORM = " - 결과: %s\n";
    private static final String MATCH_RESULT_INTRO = "\n## 최종 승패";
    private static final String DEALER_MATCH_RESULT_PREFIX = "딜러: ";
    private static final String WIN_MESSAGE = "승";
    private static final String LOSE_MESSAGE = "패";
    private static final String PUSH_MESSAGE = "무";
    private static final String DEALER_MATCH_RESULT_FORM = "%s승 %s패 %s무";
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

    public void printMatchResult(final List<PlayerMatchResultOutcome> playerMatchResultOutcomes) {
        System.out.println(MATCH_RESULT_INTRO);
        System.out.println(formatDealerMatchResult(playerMatchResultOutcomes));
        for (PlayerMatchResultOutcome playerMatchResultOutcome : playerMatchResultOutcomes) {
            System.out.println(formatPlayerMatchResult(playerMatchResultOutcome));
        }
    }

    private String formatDealerMatchResult(final List<PlayerMatchResultOutcome> playerMatchResultOutcomes) {
        Map<MatchResult, Long> outcomeCounts = playerMatchResultOutcomes.stream()
                .collect(Collectors.groupingBy(PlayerMatchResultOutcome::matchResult, Collectors.counting()));
        long winCount = outcomeCounts.getOrDefault(MatchResult.LOSE, 0L);
        long loseCount = outcomeCounts.getOrDefault(MatchResult.WIN, 0L);
        loseCount += outcomeCounts.getOrDefault(MatchResult.BLACKJACK_WIN, 0L);
        long pushCount = outcomeCounts.getOrDefault(MatchResult.PUSH, 0L);
        return DEALER_MATCH_RESULT_PREFIX + String.format(DEALER_MATCH_RESULT_FORM, winCount, loseCount, pushCount);
    }

    private String formatPlayerMatchResult(final PlayerMatchResultOutcome playerMatchResultOutcome) {
        return playerMatchResultOutcome.name() + ": " + formatMatchResult(playerMatchResultOutcome.matchResult());
    }

    private String formatMatchResult(final MatchResult matchResult) {
        if (matchResult == MatchResult.WIN || matchResult == MatchResult.BLACKJACK_WIN) {
            return WIN_MESSAGE;
        }
        if (matchResult == MatchResult.LOSE) {
            return LOSE_MESSAGE;
        }
        return PUSH_MESSAGE;
    }

    public void printBettingProfits(final List<BettingProfitOutcome> bettingProfitOutcomes) {
        System.out.println(formatBettingProfits(bettingProfitOutcomes));
    }

    private String formatBettingProfits(final List<BettingProfitOutcome> bettingProfitOutcomes) {
        return bettingProfitOutcomes.stream()
                .map(this::formatBettingProfit)
                .collect(Collectors.joining("\n"));
    }

    private String formatBettingProfit(final BettingProfitOutcome bettingProfitOutcome) {
        return bettingProfitOutcome.name() + ": " + bettingProfitOutcome.profit();
    }

    public void printException(final String errorMessage) {
        System.out.println(ERROR_MESSAGE_PREFIX + errorMessage);
    }
}
