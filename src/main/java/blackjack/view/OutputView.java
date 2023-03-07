package blackjack.view;

import blackjack.domain.game.BlackjackGameResult;
import blackjack.domain.game.Result;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.lang.String.join;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final int INITIAL_DRAW_COUNT = 2;
    private static final String INITIAL_DRAW_MESSAGE = "에게 " + INITIAL_DRAW_COUNT + "장을 나누었습니다.";
    private static final String DELIMITER = ", ";
    private static final String PLAYER_CARD_MESSAGE_FORMAT = "%s 카드: %s";
    private static final String PLAYER_SCORE_MESSAGE_FORMAT = " - 결과: %d";
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String DEALER_DRAW_MESSAGE = NEW_LINE + "딜러는 16이하라 한 장의 카드를 더 받았습니다." + NEW_LINE;
    private static final String GAME_RESULT_MESSAGE = NEW_LINE + "##최종 승패";
    private static final String GAME_RESULT_PLAYER_MESSAGE_FORMAT = "%s: %s";
    private static final String WIN_MESSAGE = "승";
    private static final String DRAW_MESSAGE = "무";
    private static final String LOSE_MESSAGE = "패";
    private static final String GAME_RESULT_DEALER_MESSAGE_FORMAT =
            "딜러: %d" + WIN_MESSAGE + " %d" + DRAW_MESSAGE + " %d" + LOSE_MESSAGE;

    public void printInitialDraw(final List<Player> players) {
        System.out.println(NEW_LINE + generateNames(players) + INITIAL_DRAW_MESSAGE);
        for (Player player : players) {
            printPlayerMessage(player, generateCardMessages(player));
        }
        System.out.print(NEW_LINE);
    }

    private String generateNames(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private void printPlayerMessage(final Player player, final String message) {
        System.out.printf((PLAYER_CARD_MESSAGE_FORMAT) + "%n", player.getName(), message);
    }

    private String generateCardMessages(final Player player) {
        if (player.isDealer()) {
            Dealer dealer = (Dealer) player;
            return dealer.getFirstCardLetter();
        }
        return join(DELIMITER, player.getCardLetters());
    }

    public void printDealerDraw(final Dealer dealer) {
        System.out.println(DEALER_DRAW_MESSAGE.repeat(dealer.getCardCount() - INITIAL_DRAW_COUNT));
    }

    public void printDrawResult(final Player player) {
        printPlayerMessage(player, generateCardMessages(player));
    }

    public void printPlayerResult(final Player player) {
        printPlayerMessage(player, generateCardMessages(player) + generateScoreMessage(player));
    }

    private String generateScoreMessage(final Player player) {
        return format(PLAYER_SCORE_MESSAGE_FORMAT, player.calculateScore());
    }

    public void printGameResult(final BlackjackGameResult result) {
        System.out.println(GAME_RESULT_MESSAGE);
        System.out.println(generateDealerResultMessage(result));
        final Map<Player, Result> gameResult = result.getResult();
        for (final Player player : gameResult.keySet()) {
            System.out.println(generatePlayerResultMessage(gameResult, player));
        }
    }

    private static String generateDealerResultMessage(final BlackjackGameResult result) {
        return String.format(
                GAME_RESULT_DEALER_MESSAGE_FORMAT,
                result.calculateDealerWinCount(),
                result.calculateDealerDrawCount(),
                result.calculateDealerLoseCount()
        );
    }

    private String generatePlayerResultMessage(final Map<Player, Result> gameResult, final Player player) {
        return String.format(
                GAME_RESULT_PLAYER_MESSAGE_FORMAT,
                player.getName(),
                generateResultMessage(gameResult.get(player))
        );
    }

    private String generateResultMessage(final Result result) {
        if (result == Result.WIN) {
            return WIN_MESSAGE;
        }
        if (result == Result.DRAW) {
            return DRAW_MESSAGE;
        }
        return LOSE_MESSAGE;
    }

    public void printError(final String message) {
        System.out.println(ERROR_MESSAGE + message);
    }
}
