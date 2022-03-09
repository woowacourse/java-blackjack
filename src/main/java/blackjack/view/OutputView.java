package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import java.util.List;

public class OutputView {

    private OutputView() {
    }

    public static void printStartInfo(Dealer dealer, List<Player> players) {
        String names = players.stream()
                .map(Player::getName)
                .collect(joining(", "));
        System.out.println("\n딜러와 " + names + "에게 2장씩 나누었습니다.");

        System.out.println("딜러: " + cardInfo(dealer.getCards().get(0)));
        for (Player player : players) {
            printPlayerCardInfo(player);
        }
    }

    public static void printPlayerCardInfo(Player player) {
        String cardsInfo = player.getCards().stream()
                .map(card -> cardInfo(card))
                .collect(joining(", "));

        System.out.println(player.getName() + ": " + cardsInfo);
    }

    private static String cardInfo(Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }
}

