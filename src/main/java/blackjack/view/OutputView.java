package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.model.card.Cards;
import blackjack.model.game.Rule;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.User;

public class OutputView {

    public void printDealInitialCardsResult(final Dealer dealer, final List<User> users, final Rule rule) {
        String userNames = users.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        System.out.println();
        System.out.println(dealer.getName() + "와 " + userNames + "에게 2장을 나누었습니다.");
        System.out.println(dealer.getName() + "카드: " + formatCards(rule.openDealerCards(dealer)));
        users.forEach(user -> {
            System.out.println(user.getName() + "카드: " + formatCards(user.getCards()));
        });
    }

    private String formatCards(final Cards cards) {
        return cards.getValues()
                .stream()
                .map(card -> card.cardNumber().getName() + card.cardType().getName())
                .collect(Collectors.joining(", "));
    }

}
