package view;

import domain.card.Card;
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
        System.out.printf("%s카드: %s%n",
                player.getName(), convertToCardsFormat(cards));
    }

    public static void printPlayerCardsAndSum(String name, Cards cards, int sum) {
        System.out.printf("%s카드: %s - 결과: %d%n",
                name, convertToCardsFormat(cards), sum);
    }

    private static String convertToCardsFormat(Cards cards) {
        return String.join(", ", cards.getCards().stream()
                .map(OutputView::convertToCardFormat)
                .toList());
    }

    private static String convertToCardFormat(Card card) {
        return String.format("%s%s", card.getRank().getTitle(), card.getSuit().getTitle());
    }
}
