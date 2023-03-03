package blackjack.view;

import static java.lang.String.format;
import static java.lang.String.join;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final int INITIAL_DRAW_COUNT = 2;
    private static final String INITIAL_DRAW_MESSAGE = "에게 " + INITIAL_DRAW_COUNT + "장을 나누었습니다.";
    private static final String DELIMITER = ", ";
    private static final String PLAYER_CARD_MESSAGE_FORMAT = "%s 카드: %s";
    private static final String PLAYER_SCORE_MESSAGE_FORMAT = " - 결과: %d";
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String DEALER_DRAW_MESSAGE = NEW_LINE + "딜러는 16이하라 한 장의 카드를 더 받았습니다." + NEW_LINE;

    public void printInitialDraw(final Players players) {
        System.out.println(NEW_LINE + generateNames(players) + INITIAL_DRAW_MESSAGE);
        for (Player player : players.getPlayers()) {
            printPlayerMessage(player, generateCardMessage(player));
        }
        System.out.print(NEW_LINE);
    }

    private String generateNames(final Players players) {
        return join(DELIMITER, players.getNames());
    }

    private void printPlayerMessage(final Player player, final String message) {
        System.out.println(format(PLAYER_CARD_MESSAGE_FORMAT, player.getName(), message));
    }

    private String generateCardMessage(final Player player) {
        return join(DELIMITER, player.getCardLetters());
    }

    public void printDealerDraw(final Dealer dealer) {
        System.out.println(DEALER_DRAW_MESSAGE.repeat(dealer.getCardCount() - INITIAL_DRAW_COUNT));
    }

    public void printDrawResult(final Player player) {
        printPlayerMessage(player, generateCardMessage(player));
    }

    public void printPlayerResult(final Player player) {
        printPlayerMessage(player, generateCardMessage(player) + generateScoreMessage(player));
    }

    private String generateScoreMessage(final Player player) {
        return format(PLAYER_SCORE_MESSAGE_FORMAT, player.calculateScore());
    }

    public void printError(final String message) {
        System.out.println(ERROR_MESSAGE + message);
    }
}
