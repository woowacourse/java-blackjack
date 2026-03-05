package blackjack.view;

import blackjack.model.Card;
import blackjack.model.Dealer;
import blackjack.model.Player;
import java.util.List;

public class OutputView {
    public static void printInitCards(List<Player> players, Dealer dealer) {
        List<String> names = players.stream()
                .map(Player::getName)
                .toList();
        StringBuilder sb = new StringBuilder();
        sb.append("딜러와 ");
        sb.append(String.join(", ", names));
        sb.append("에게 2장을 나누었습니다.");
        System.out.println(sb);

        printDealerCard(dealer);
        for (Player player : players) {
            printPlayerCards(player);
        }
    }

    private static void printDealerCard(Dealer dealer) {
        System.out.println("딜러카드: " + dealer.getCardStatus().getCards().getFirst().getFormat());
    }

    public static void printPlayerCards(Player player) {
        List<String> formats = player.getCardStatus().getCards().stream()
                .map(Card::getFormat)
                .toList();

        System.out.println(player.getName() + "카드: " + String.join(", ", formats));
    }
}
