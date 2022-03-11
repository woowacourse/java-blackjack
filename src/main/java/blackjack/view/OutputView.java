package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printPlayerInitialCards(final List<User> users, final Dealer dealer) {
        System.out.printf("%n%s와 %s에게 2장의 카드를 나누었습니다.", dealer.getName(), getUserNames(users));
        System.out.printf("%n%s카드: %s", dealer.getName(), printCards(dealer.openFirstCards()));
        for (User user : users) {
            System.out.printf("%n%s카드: %s", user.getName(), printCards(user.openFirstCards()));
        }
    }

    public static String getUserNames(final List<User> users) {
        return users.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    private static String printCards(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getCardNumber().getValue() + card.getCardPattern().getName())
                .collect(Collectors.joining(", "));
    }
}
