package blackjack.view;

import blackjack.model.Card;
import blackjack.model.Dealer;
import blackjack.model.Player;
import blackjack.model.User;
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

    public static void printCantAddCard() {
        System.out.println("카드의 합계가 21 이상입니다. 더이상 카드를 받을 수 없습니다.");
    }

    public static void printDealerHit() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardStatus(User user, int totalScore) {
        StringBuilder sb = new StringBuilder();

        sb.append(user.getName() + "카드: ");
        List<String> cardFormats = user.getCardStatus().getCards().stream().map(Card::getFormat).toList();
        sb.append(String.join(", ", cardFormats));
        sb.append(" - 결과: " + totalScore).append("\n");

        System.out.println(sb);
    }

}
