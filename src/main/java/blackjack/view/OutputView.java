package blackjack.view;

import blackjack.model.Card;
import blackjack.model.Dealer;
import blackjack.model.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {}

    public static void printOpenCard(Dealer dealer, List<Player> gamers) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.name(), gamerNames(gamers));
        System.out.printf("%s: %s%n", dealer.name(), openCards(dealer));
        for (Player gamer : gamers) {
            System.out.printf("%s카드: %s%n", gamer.name(), openCards(gamer));
        }
    }

    public static void printCard(Player player) {
        System.out.printf("%s: %s%n", player.name(), cards(player));
    }

    private static String gamerNames(List<Player> gamers) {
        return gamers.stream()
            .map(Player::name)
            .collect(Collectors.joining(", "));
    }

    private static String cards(Player player) {
        return player.cards().stream().map(card -> card(card)).collect(Collectors.joining(", "));
    }

    private static String openCards(Player player) {
        return player.openCards().stream().map(card -> card(card)).collect(Collectors.joining(", "));
    }

    private static String card(Card card) {
        return card.rank().symbol() + card.suit().symbol();
    }
}
