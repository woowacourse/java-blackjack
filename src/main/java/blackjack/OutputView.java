package blackjack;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static String joinPlayerNamesWithComma(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public void printInitialCards(Card dealerVisibleCard, List<Player> players) {
        System.out.printf("%n딜러와 %s에게 2장을 나누었습니다.%n", joinPlayerNamesWithComma(players));
        System.out.printf("딜러카드: %s%n", dealerVisibleCard.getDisplayLabel());
        for (Player player : players) {
            printPlayerHand(player);
        }
        System.out.println();
    }

    private String getHand(Player player) {
        return player.getHand()
                .stream()
                .map(Card::getDisplayLabel)
                .collect(Collectors.joining(", "));
    }

    public void printPlayerHand(Player player) {
        System.out.printf("%s: %s%n", player.getName(), getHand(player));
    }

    public void printDealerHit(boolean isDealerHit) {
        if (isDealerHit) {
            System.out.printf("%n딜러는 %d이하라 한장의 카드를 더 받았습니다.%n", Game.DEALER_HIT_THRESHOLD);
            return;
        }
        System.out.println();
    }
}
