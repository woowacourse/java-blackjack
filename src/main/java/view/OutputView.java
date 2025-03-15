package view;

import domain.card.Cards;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Users;
import java.util.List;

public class OutputView {

    public static void printInitialCards(Dealer dealer, Users users) {
        List<String> names = users.getUsers().stream()
                .map(Player::getName)
                .toList();

        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), String.join(", ", names));

        printPlayerCards(dealer, dealer.openedCards());
        users.getUsers().forEach(user -> printPlayerCards(user, user.openedCards()));
        System.out.println();
    }

    public static void printPlayerCards(Player player, Cards cards) {
        System.out.printf("%s카드: %s%n", player.getName(),
                String.join(", ", cards.getCards().stream()
                        .map(card -> String.format("%s%s", card.getRank().getTitle(),
                                card.getSuit().getTitle()))
                        .toList()));
    }
}
