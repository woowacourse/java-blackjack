package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class OutputView {

    public static void printPlayersCard(Players players, Player dealer) {
        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(),
            players.getValue().stream().map(Player::getName).collect(Collectors.joining(", ")));
        printOpenCard(players.getValue(), dealer);
    }

    private static void printOpenCard(List<Player> players, Player dealer) {
        Card dealerOpenCard = dealer.getHoldCards().get(0);
        System.out.printf("%s: %s%s%n", dealer.getName(), dealerOpenCard.getCardNumber().getName(),
            dealerOpenCard.getPatternName());
        for (Player player : players) {
            printPlayerCards(player);
        }
    }

    public static void printPlayerCards(Player player) {
        System.out.printf("%s: %s%n", player.getName(),
            player.getHoldCards()
                .stream()
                .map(card -> card.getCardNumber().getName() + card.getPatternName())
                .collect(Collectors.joining(", ")));
    }

    private static void printPlayerResult(Player player) {
        System.out.printf("%s: %s - 결과: %d%n",
            player.getName(),
            player.getHoldCards()
                .stream()
                .map(card -> card.getCardNumber().getName() + card.getPatternName())
                .collect(Collectors.joining(", ")),
            player.getTotalNumber());
    }

    public static void printPlayersResult(Players players, Player dealer) {
        System.out.println();
        printPlayerResult(dealer);

        for (Player player : players.getValue()) {
            printPlayerResult(player);
        }
    }

    public static void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printScoreResult(Map<Player, Integer> playersProfit) {
        System.out.println(System.lineSeparator() + "## 최종 수익");
        playersProfit.forEach((player, profit) -> System.out.printf("%s : %d%n", player.getName(), profit));
    }

    public static void printException(Exception exception) {
        System.out.println(exception.getMessage());
    }
}
