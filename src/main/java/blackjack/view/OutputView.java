package blackjack.view;

import static java.lang.String.format;
import static java.lang.String.join;

import blackjack.domain.game.Bets;
import blackjack.domain.game.Money;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final int INITIAL_DRAW_COUNT = 2;
    private static final int DEALER_OPEN_CARD_INDEX = 0;
    private static final String INITIAL_DRAW_MESSAGE = "에게 " + INITIAL_DRAW_COUNT + "장을 나누었습니다.";
    private static final String DELIMITER = ", ";
    private static final String PLAYER_CARD_MESSAGE_FORMAT = "%s 카드: %s";
    private static final String PLAYER_SCORE_MESSAGE_FORMAT = " - 결과: %d";
    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String DEALER_DRAW_MESSAGE = NEW_LINE + "딜러는 16이하라 한 장의 카드를 더 받았습니다." + NEW_LINE;
    private static final String GAME_RESULT_MESSAGE = NEW_LINE + "## 최종 수익";
    private static final String GAME_RESULT_MESSAGE_FORMAT = "%s: %s";

    public void printInitialDraw(final List<Player> players) {
        System.out.println(NEW_LINE + generateNames(players) + INITIAL_DRAW_MESSAGE);
        System.out.println(generateInitialDrawMessages(players) + NEW_LINE);
    }

    private String generateNames(final List<Player> players) {
        return players.stream()
                .map(Player::getNameValue)
                .collect(Collectors.joining(DELIMITER));
    }

    private String generateInitialDrawMessages(final List<Player> players) {
        return players.stream()
                .map(this::generateInitialDrawMessage)
                .collect(Collectors.joining(NEW_LINE));
    }

    private String generateInitialDrawMessage(final Player player) {
        if (player.isDealer()) {
            return generatePlayerMessage(player, player.getSymbols().get(DEALER_OPEN_CARD_INDEX));
        }
        return generatePlayerMessage(player, generateCardMessage(player));
    }

    private String generatePlayerMessage(final Player player, final String message) {
        return format(PLAYER_CARD_MESSAGE_FORMAT, player.getNameValue(), message);
    }

    private String generateCardMessage(final Player player) {
        return join(DELIMITER, player.getSymbols());
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

    public void printGameResult(final Bets bets) {
        System.out.println(GAME_RESULT_MESSAGE);
        System.out.println(String.format(GAME_RESULT_MESSAGE_FORMAT, "딜러", bets.getDealerProfit().getValue()));
        System.out.println(generateGameResultMessage(bets.getBets()));
    }

    private String generateGameResultMessage(final Map<Player, Money> bets) {
        return bets.keySet().stream()
                .map(player -> String.format(
                        GAME_RESULT_MESSAGE_FORMAT,
                        player.getNameValue(),
                        bets.get(player).getValue())
                )
                .collect(Collectors.joining(NEW_LINE));
    }

    public void printError(final String message) {
        System.out.println(ERROR_MESSAGE + message);
    }
}
