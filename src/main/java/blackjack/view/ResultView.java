package blackjack.view;

import blackjack.domain.player.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultView {

    public static void printUsersCards(List<Player> users) {
        for (Player user : users) {
            printUserCards(user);
        }
    }

    public static void printUserCards(Player user) {
        System.out.println(makeUserCardsToString(user));
    }

    private static String makeUserCardsToString(Player user) {
        StringBuilder sb = new StringBuilder();
        sb.append(user.getName())
                .append("카드: ");
        List<String> userCards = user.getCards().stream()
                .map(card -> card.getCardNumberType() + card.getCardPattern())
                .collect(Collectors.toList());
        sb.append(String.join(", ", userCards));
        return sb.toString();
    }

    public static void printDealerCard(Player dealer) {
        System.out.print(dealer.getName() + "카드: ");
        String dealerCard = dealer.getCards().stream()
                .map(card -> card.getCardNumberType() + card.getCardPattern())
                .findFirst()
                .orElseThrow();
        System.out.println(dealerCard);
    }

    public static void printDealerReceiveCard() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printTotalCardResult(Player dealer, List<Player> users) {
        System.out.println();
        System.out.println(makeUserCardsToString(dealer) + " - 결과: " + dealer.getTotalScore());
        for (Player user : users) {
            System.out.println(makeUserCardsToString(user) + " - 결과: " + user.getTotalScore());
        }
    }
}
