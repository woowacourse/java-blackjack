package blackjack.view;

import blackjack.domain.player.Player;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    public static void printUsersCards(List<Player> users) {
        for (Player user : users) {
            printUserCards(user);
        }
    }

    public static void printUserCards(Player user) {
        System.out.print(user.getName() + "카드: ");
        List<String> userCards = user.getCards().stream()
                .map(card -> card.getCardNumberType() + card.getCardPattern())
                .collect(Collectors.toList());
        System.out.println(String.join(", ", userCards));
    }

    public static void printDealerCard(Player dealer) {
        System.out.print(dealer.getName() + "카드: ");
        String dealerCard = dealer.getCards().stream()
                .map(card -> card.getCardNumberType() + card.getCardPattern())
                .findFirst()
                .orElseThrow();
        System.out.println(dealerCard);
    }
}
