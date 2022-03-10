package blackjack;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NAME_DELIMITER = ", ";

    public static void printPlayersDefaultCard(List<Player> players) {
        System.out.println(MessageFormat.format("딜러와 {0}에게 2장의 카드를 나누었습니다.", concatPlayerName(players)));
        for (Player player : players) {
            printPlayerCards(player);
        }
    }

    public static void printPlayerCards(Player player) {
        System.out.println(MessageFormat.format("{0}카드: {1}", player.getName(), concatCardName(player.getHoldCards())));
    }

    public static void printReceivingMoreCardOfDealer() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    private static String concatPlayerName(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(NAME_DELIMITER));
    }

    private static String concatCardName(HoldCards holdCards) {
        return holdCards.getCards()
                .stream()
                .map(OutputView::toCardName)
                .collect(Collectors.joining(NAME_DELIMITER));
    }

    private static String toCardName(Card card) {
        return card.getNumber().getName() + card.getSuit().getName();
    }
}
