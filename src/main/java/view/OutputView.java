package view;

import domain.card.Card;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.User;
import domain.profit.Profit;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printInitialCards(Dealer dealer, List<User> users) {
        List<String> names = users.stream()
                .map(Player::getName)
                .toList();

        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), String.join(", ", names));

        printPlayerCards(dealer, dealer.getOpenedCards());
        users.forEach(user -> printPlayerCards(user, user.getOpenedCards()));
        System.out.println();
    }

    public static void printPlayerCards(Player player, List<Card> cards) {
        System.out.printf("%s카드: %s%n",
                player.getName(),
                String.join(", ", cards.stream()
                        .map(OutputView::convertToCardFormat)
                        .toList()));
    }

    public static void printCardsAndSum(Dealer dealer,
                                        List<User> users,
                                        Map<Player, Integer> playerSum) {
        printPlayerCardsAndSum(dealer, playerSum.get(dealer));
        users.forEach(player -> printPlayerCardsAndSum(player, playerSum.get(player)));
        System.out.println();
    }

    private static void printPlayerCardsAndSum(Player player, int sum) {
        System.out.printf("%s카드: %s - 결과: %d%n",
                player.getName(),
                String.join(", ", player.getCards().stream()
                        .map(OutputView::convertToCardFormat)
                        .toList()),
                sum);
    }

    public static void printDealerHitMessage() {
        System.out.printf("딜러는 16이하라 한장의 카드를 더 받았습니다.%n%n");
    }

    public static void printProfit(Map<Dealer, Profit> dealerProfit, Map<User, Profit> usersProfit) {
        Map.Entry<Dealer, Profit> dealer = dealerProfit.entrySet().iterator().next();

        System.out.printf("%s: %d%n", dealer.getKey().getName(), dealer.getValue().getProfit());
        usersProfit.forEach((user, profit) -> System.out.printf("%s: %d%n", user.getName(), profit.getProfit()));
    }

    private static String convertToCardFormat(Card card) {
        return String.format("%s%s", card.getRank().getTitle(), card.getSuit().getTitle());
    }
}
