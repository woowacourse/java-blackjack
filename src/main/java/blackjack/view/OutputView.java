package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.Result;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ERROR_MARK = "[ERROR] ";
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final String BUST_MESSAGE = "카드의 합이 21을 넘어, 게임에서 패배하였습니다.";
    private static final String DEALER_MORE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DISTRIBUTE_MESSAGE = "딜러와 %s에게 2장의 카드를 나누어주었습니다.";
    private static final String CARD_STATUS_FORMAT = "%s카드: %s";
    private static final String CARD_RESULT_FORMAT = "%s카드: %s - 결과: %d";
    private static final String GAME_RESULT_MESSAGE = "## 최종 승패";

    public static void showDistributedCard(final List<Player> players, final Dealer dealer) {
        distributeMessage(players);
        showInitialCard(dealer);
        showPlayersCard(players);
    }

    private static void distributeMessage(final List<Player> players) {
        final String names = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        System.out.printf(NEWLINE + DISTRIBUTE_MESSAGE + NEWLINE, names);
    }

    public static void showInitialCard(final Participant participant) {
        final String cardStatus = participant.showInitialCards().stream()
                .map(OutputView::cardFormat)
                .collect(Collectors.joining(", "));
        System.out.printf(CARD_STATUS_FORMAT + NEWLINE, participant.getName(), cardStatus);
    }

    private static void showPlayersCard(final List<Player> players) {
        players.forEach(OutputView::showInitialCard);
    }

    public static void showPlayerCard(final Player player) {
        final String cardStatus = player.getCards().stream()
                .map(OutputView::cardFormat)
                .collect(Collectors.joining(", "));
        System.out.printf(CARD_STATUS_FORMAT, player.getName(), cardStatus + NEWLINE);
    }

    private static String cardFormat(final Card card) {
        return card.getCardSymbol() + card.getCardType();
    }

    public static void showFinalCardResult(final List<Player> players, final Dealer dealer) {
        System.out.println();
        showCardResult(dealer);
        showCardsResult(players);
    }

    private static void showCardResult(final Participant participant) {
        final String cardStatus = participant.getCards().stream()
                .map(OutputView::cardFormat)
                .collect(Collectors.joining(", "));
        System.out.printf(CARD_RESULT_FORMAT + NEWLINE, participant.getName(), cardStatus, participant.calculateScore());
    }

    private static void showCardsResult(final List<Player> players) {
        for (final Player player : players) {
            showCardResult(player);
        }
    }

    public static void showGameResult(final Dealer dealer, final List<Integer> resultCounts) {
        System.out.println(NEWLINE + GAME_RESULT_MESSAGE);
        showDealerGameResult(dealer, resultCounts);
    }

    private static void showDealerGameResult(final Dealer dealer, final List<Integer> resultCounts) {
        System.out.printf("%s: %d%s %d%s %d%s" + NEWLINE, dealer.getName(),
                resultCounts.get(0), Result.WIN.getValue(),
                resultCounts.get(1), Result.LOSE.getValue(),
                resultCounts.get(2), Result.DRAW.getValue());
    }

    public static void showPlayersGameResult(final Map<Player, Result> playersResult) {
        playersResult.forEach(OutputView::showPlayerGameResult);
    }

    private static void showPlayerGameResult(final Player player, final Result result) {
        System.out.printf("%s: %S" + NEWLINE, player.getName(), result.getValue());
    }

    public static void showBustMessage() {
        System.out.println(BUST_MESSAGE);
    }

    public static void dealerMoreCard() {
        System.out.println(NEWLINE + DEALER_MORE_CARD_MESSAGE);
    }

    public static void showErrorMessage(final String message) {
        System.out.println(ERROR_MARK + message);
    }
}