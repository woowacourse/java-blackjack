package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    public static void printInitialHandOfEachPlayer(final Dealer dealer, final List<Player> players) {
        printInitialDistributionMessage(dealer, players);
        printDealerCard(dealer);
        players.forEach(OutputView::printPlayerCard);
    }

    private static void printInitialDistributionMessage(final Dealer dealer, final List<Player> players) {
        final String names = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));

        printLineSeparator();
        final String initialDistributionMessage = String.format("%s와 %s에게 2장을 나누었습니다.", dealer.getName(), names);
        System.out.println(initialDistributionMessage);
    }

    private static void printDealerCard(final Dealer dealer) {
        final Card card = dealer.getFirstCard();
        System.out.println(dealer.getName() + ": " + getCardInfo(card));
    }

    public static void printPlayerCard(final Player player) {
        final String playerCardInfo = getPlayerCardInfo(player);
        System.out.println(playerCardInfo);
    }

    public static void printDealerHitMessage(final Dealer dealer) {
        final String dealerHitMessage = String.format("%s는 16이하라 한장의 카드를 더 받았습니다.", dealer.getName());
        System.out.println(dealerHitMessage);
        printLineSeparator();
    }

    public static void printPlayerCardWithScore(final Player player) {
        final String playerCardInfo = getPlayerCardInfo(player);
        System.out.println(playerCardInfo + " - 결과: " + player.getScore());
    }

    private static String getPlayerCardInfo(final Player player) {
        return player.getName() + "카드: " +
                player.getCards()
                        .stream()
                        .map(OutputView::getCardInfo)
                        .collect(Collectors.joining(", "));
    }

    private static String getCardInfo(final Card card) {
        return CardNumberStrings.mapCardNumberToString(card.getNumber()) +
                CardShapeStrings.mapCardShapeToString(card.getShape());
    }

    // TODO: 개선 필요
    public static void printProfits(final Dealer dealer) {
        printLineSeparator();
        System.out.println("## 최종 수익");
        System.out.println(String.format("%s: %d", dealer.getName(), dealer.findDealerProfit()));

        final Map<Player, Integer> playerProfits = dealer.findPlayerProfits();
        playerProfits.forEach(
                (player, profit) -> System.out.println(String.format("%s: %d", player.getName(), profit)));
    }

    private static void printLineSeparator() {
        System.out.println();
    }
}
