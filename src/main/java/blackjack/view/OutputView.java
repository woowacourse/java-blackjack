package blackjack.view;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Players;
import java.util.Map;
import java.util.stream.Collectors;

public final class OutputView {

    private static final String DELIMITER = ", ";

    private OutputView() {
    }

    public static void printStartingCardsStatuses(Dealer dealer, Players players) {
        String names = players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));

        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");
        System.out.println(Formatter.parseStartCardStatus(dealer));
        for (Player player : players.getPlayers()) {
            System.out.println(Formatter.parseCardStatus(player));
        }
    }

    public static void printMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardResult(Player player) {
        System.out.println(Formatter.parseCardResult(player));
    }

    public static void printBustedPlayer(Player player) {
        System.out.println(player.getName() + "는 버스트되어 더 이상 카드를 뽑을 수 없습니다!");
    }

    public static void printRevenue(int playersTotalRevenue, Map<Player, Integer> revenueMap) {
        System.out.printf("딜러: %s%n", -playersTotalRevenue);
        System.out.println(Formatter.parsePlayerRevenue(revenueMap));
    }
}
