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
    private static final String GAME_PROFIT_TITLE = NEW_LINE + "##최종 수익";
    private static final String GAME_GAMBLER_PROFIT_MESSAGE_FORMAT = "%s: %s";
    private static final String GAME_DEALER_PROFIT_MESSAGE_FORMAT = "딜러: %d";

    public void printInitialDraw(final List<Player> players) {
        System.out.println(NEW_LINE + generateNamesMessage(players) + INITIAL_DRAW_MESSAGE);
        for (Player player : players) {
            System.out.println(generatePlayerCardMessage(player, generateInitialCardMessages(player)));
        }
        System.out.print(NEW_LINE);
    }

    private String generateNamesMessage(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));
    }

    private String generatePlayerCardMessage(final Player player, final String message) {
        return format(PLAYER_CARD_MESSAGE_FORMAT, player.getName(), message);
    }

    private String generateInitialCardMessages(final Player player) {
        if (player.isDealer()) {
            Dealer dealer = (Dealer) player;
            return dealer.getFirstCardLetter();
        }
        return generateCardMessage(player);
    }

    private String generateCardMessage(final Player player) {
        return join(DELIMITER, player.getCardLetters());
    }

    public void printDealerDraw(final Dealer dealer) {
        System.out.println(DEALER_DRAW_MESSAGE.repeat(dealer.getCardCount() - INITIAL_DRAW_COUNT));
    }

    public void printDrawResult(final Player player) {
        System.out.println(generatePlayerCardMessage(player, generateInitialCardMessages(player)));
    }

    public void printPlayerCardAndScores(final Player player) {
        final String cardAndScoreMessages = generateCardMessage(player) + generateScoreMessage(player);
        System.out.println(generatePlayerCardMessage(player, cardAndScoreMessages));
    }

    private String generateScoreMessage(final Player player) {
        return format(PLAYER_SCORE_MESSAGE_FORMAT, player.calculateScore());
    }

    public void printPlayerProfits(final Map<Player, Money> profitByPlayers) {
        System.out.println(GAME_PROFIT_TITLE);
        System.out.println(generateDealerProfit(profitByPlayers));
        System.out.println(generateGamblerProfits(profitByPlayers));
    }

    private String generateDealerProfit(final Map<Player, Money> profitByPlayers) {
        return format(GAME_DEALER_PROFIT_MESSAGE_FORMAT, calculateDealerProfit(profitByPlayers));
    }

    private int calculateDealerProfit(final Map<Player, Money> profitByPlayers) {
        return -profitByPlayers.values()
                .stream()
                .mapToInt(Money::getAmount)
                .sum();
    }

    private String generateGamblerProfits(final Map<Player, Money> profitByPlayers) {
        return profitByPlayers.keySet()
                .stream()
                .map(player -> format(GAME_GAMBLER_PROFIT_MESSAGE_FORMAT, player.getName(), profitByPlayers.get(player).getAmount()))
                .collect(Collectors.joining(NEW_LINE));
    }

    public void printError(final String message) {
        System.out.println(ERROR_MESSAGE + message);
    }
}
