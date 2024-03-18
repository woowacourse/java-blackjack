package blackjack.view;

import blackjack.view.dto.DealerFinalCardsOutcome;
import blackjack.view.dto.ExecutingPlayer;
import blackjack.view.dto.PlayerEarning;
import blackjack.view.dto.PlayerFinalCardsOutcome;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DEALING_RESULT_INTRO = "\n딜러와 %s에게 2장을 나누었습니다.\n";
    private static final String PLAYER_CARDS_FORM = "%s카드: %s";
    private static final String DEALER_ACTION_FORM = "\n딜러는 16이하라 %d장의 카드를 더 받았습니다.\n";
    private static final String DEALER_CARDS_FORM = "\n딜러 카드: %s";
    private static final String TOTAL_SCORE_FORM = " - 결과: %s\n";
    private static final String FINAL_EARNING_INTRO = "\n## 최종 수익";
    public static final String DEALER_EARNING_PREFIX = "딜러: ";
    public static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    public void printDealingResult(final List<ExecutingPlayer> executingPlayers, final String dealerCard) {
        String names = formatNames(executingPlayers);
        System.out.printf(DEALING_RESULT_INTRO, names);
        System.out.printf(DEALER_CARDS_FORM, dealerCard);
        System.out.println();
        for (ExecutingPlayer player : executingPlayers) {
            System.out.println(formatPlayerCards(player));
        }
    }

    private String formatNames(final List<ExecutingPlayer> executingPlayers) {
        return executingPlayers.stream()
                .map(ExecutingPlayer::name)
                .collect(Collectors.joining(", "));
    }

    private String formatPlayerCards(final ExecutingPlayer player) {
        return String.format(PLAYER_CARDS_FORM, player.name(), player.cards());
    }

    public void printPlayerActionResult(final ExecutingPlayer player) {
        System.out.println(formatPlayerCards(player));
    }

    public void printDealerActionResult(final int dealerActionCount) {
        System.out.printf(DEALER_ACTION_FORM, dealerActionCount);
    }

    public void printDealerFinalCards(final DealerFinalCardsOutcome dealerFinalCardsOutcome) {
        String dealerCards = dealerFinalCardsOutcome.cards();
        int dealerTotalScore = dealerFinalCardsOutcome.totalScore();
        System.out.printf(DEALER_CARDS_FORM, dealerCards);
        System.out.printf(TOTAL_SCORE_FORM, dealerTotalScore);
    }

    public void printPlayersFinalCards(final List<PlayerFinalCardsOutcome> playerFinalCardsOutcomes) {
        for (PlayerFinalCardsOutcome playerOutcome : playerFinalCardsOutcomes) {
            String playerName = playerOutcome.name();
            String playerCards = playerOutcome.cards();
            int playerTotalScore = playerOutcome.totalScore();
            System.out.printf(PLAYER_CARDS_FORM, playerName, playerCards);
            System.out.printf(TOTAL_SCORE_FORM, playerTotalScore);
        }
    }

    public void printEarnings(final List<PlayerEarning> playerEarnings) {
        System.out.println(FINAL_EARNING_INTRO);
        System.out.println(formatDealerEarning(playerEarnings));
        for (PlayerEarning playerEarning : playerEarnings) {
            System.out.println(formatPlayerEarning(playerEarning));
        }
    }

    private String formatDealerEarning(final List<PlayerEarning> playerEarnings) {
        int earning = (-1) * playerEarnings.stream()
                .mapToInt(PlayerEarning::earning)
                .sum();
        return DEALER_EARNING_PREFIX + earning;
    }

    private String formatPlayerEarning(final PlayerEarning playerEarning) {
        return playerEarning.name() + ": " + playerEarning.earning();
    }

    public void printException(final String errorMessage) {
        System.out.println(ERROR_MESSAGE_PREFIX + errorMessage);
    }
}
