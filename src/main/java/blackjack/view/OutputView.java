package blackjack.view;

import static java.lang.String.format;
import static java.lang.String.join;

import blackjack.domain.game.BlackjackGameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Result;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final int INITIAL_DRAW_COUNT = 2;
    private static final int DEALER_OPEN_CARD_INDEX = 0;
    private static final String INITIAL_DRAW_MESSAGE = "에게 " + INITIAL_DRAW_COUNT + "장을 나누었습니다.";
    private static final String DELIMITER = ", ";
    private static final String PLAYER_CARD_MESSAGE_FORMAT = "%s 카드: %s";
    private static final String PLAYER_SCORE_MESSAGE_FORMAT = " - 결과: %d";
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String DEALER_DRAW_MESSAGE = NEW_LINE + "딜러는 16이하라 한 장의 카드를 더 받았습니다." + NEW_LINE;
    private static final String GAME_RESULT_MESSAGE = NEW_LINE + "##최종 승패";
    private static final String GAME_RESULT_PLAYER_MESSAGE_FORMAT = "%s: %s";
    private static final String WIN_MESSAGE = "승";
    private static final String PUSH_MESSAGE = "무";
    private static final String LOSE_MESSAGE = "패";
    private static final String GAME_RESULT_DEALER_MESSAGE_FORMAT =
            "딜러: %d" + WIN_MESSAGE + " %d" + PUSH_MESSAGE + " %d" + LOSE_MESSAGE;

    public void printInitialDraw(final List<Player> players) {
        System.out.println(NEW_LINE + generateNames(players) + INITIAL_DRAW_MESSAGE);
        System.out.println(generateInitialDrawMessages(players) + NEW_LINE);
    }

    private String generateNames(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private String generateInitialDrawMessages(final List<Player> players) {
        return players.stream()
                .map(this::generateInitialDrawMessage)
                .collect(Collectors.joining(NEW_LINE));
    }

    private String generateInitialDrawMessage(final Player player) {
        if (player.isDealer()) {
            return generatePlayerMessage(player, player.getCardLetters().get(DEALER_OPEN_CARD_INDEX));
        }
        return generatePlayerMessage(player, generateCardMessage(player));
    }

    private String generatePlayerMessage(final Player player, final String message) {
        return format(PLAYER_CARD_MESSAGE_FORMAT, player.getName(), message);
    }

    private String generateCardMessage(final Player player) {
        return join(DELIMITER, player.getCardLetters());
    }

    public void printDealerDraw(final Dealer dealer) {
        System.out.println(DEALER_DRAW_MESSAGE.repeat(dealer.getCardCount() - INITIAL_DRAW_COUNT));
    }

    public void printDrawResult(final Player player) {
        System.out.println(generatePlayerMessage(player, generateCardMessage(player)));
    }

    public void printPlayerResult(final Player player) {
        System.out.println(generatePlayerMessage(player, generateCardMessage(player) + generateScoreMessage(player)));
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
                result.getDealerWinCount(),
                result.getDealerPushCount(),
                result.getDealerLoseCount()
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
        if (result == Result.PUSH) {
            return PUSH_MESSAGE;
        }
        return LOSE_MESSAGE;
    }

    public void printError(final String message) {
        System.out.println(ERROR_MESSAGE + message);
    }
}
