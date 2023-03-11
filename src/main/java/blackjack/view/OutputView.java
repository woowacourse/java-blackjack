package blackjack.view;

import blackjack.domain.game.Money;
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
    private static final String GAME_RESULT_MESSAGE = NEW_LINE + "##최종 수익";
    private static final String GAME_RESULT_PLAYER_MESSAGE_FORMAT = "%s: %s";
    private static final String GAME_RESULT_DEALER_MESSAGE_FORMAT = "딜러: %d";

    public void printInitialDraw(final List<Player> players) {
        System.out.println(NEW_LINE + generateNames(players) + INITIAL_DRAW_MESSAGE);
        for (Player player : players) {
            printPlayerMessage(player, generateFirstCardMessages(player));
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

    private String generateFirstCardMessages(final Player player) {
        if (player.isDealer()) {
            Dealer dealer = (Dealer) player;
            return dealer.getFirstCardLetter();
        }
        return generateCardMessages(player);
    }

    private String generateCardMessages(final Player player) {
        return join(DELIMITER, player.getCardLetters());
    }

    public void printDealerDraw(final Dealer dealer) {
        System.out.println(DEALER_DRAW_MESSAGE.repeat(dealer.getCardCount() - INITIAL_DRAW_COUNT));
    }

    public void printDrawResult(final Player player) {
        printPlayerMessage(player, generateFirstCardMessages(player));
    }

    public void printPlayerGameResult(final Player player) {
        printPlayerMessage(player, generateCardMessages(player) + generateScoreMessage(player));
    }

    private String generateScoreMessage(final Player player) {
        return format(PLAYER_SCORE_MESSAGE_FORMAT, player.calculateScore());
    }

    public void printGameResult(final Map<Player, Money> betMoneyByPlayer) {
        System.out.println(GAME_RESULT_MESSAGE);

        final int dealerProfit = -betMoneyByPlayer.values()
                .stream()
                .mapToInt(Money::getAmount)
                .sum();

        System.out.println(String.format(GAME_RESULT_DEALER_MESSAGE_FORMAT, dealerProfit));
        for (Player player : betMoneyByPlayer.keySet()) {
            System.out.println(String.format(GAME_RESULT_PLAYER_MESSAGE_FORMAT, player.getName(), betMoneyByPlayer.get(player).getAmount()));
        }
    }

    public void printError(final String message) {
        System.out.println(ERROR_MESSAGE + message);
    }
}
