package view;

import domain.Card;
import domain.Dealer;
import domain.Player;
import domain.Players;
import java.util.stream.Collectors;

public class OutputView {
    public static void printHandOutMessage(Players players){
        String playersName = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.joining(","));

        System.out.println("딜러와 "+ playersName + "에게 2장을 나누었습니다.");
    }

    public static void printCardStatus(Players players, Dealer dealer) {
        System.out.printf("딜러카드: %s%n", getDealerCardStatus(dealer));
        for(Player player : players.getPlayers()) {
            System.out.printf("%s카드: %s%n", player.getName(), getPlayerCardStatus(player));
        }
    }

    private static String getDealerCardStatus(Dealer dealer) {
        Card firstCard = dealer.getHand().getFirst();
        return getCardStatus(firstCard);
    }

    private static String getPlayerCardStatus(Player player) {
        return player.getHand().stream()
                .map(OutputView::getCardStatus)
                .collect(Collectors.joining(", "));
    }

    private static String getCardStatus(Card card) {
        return card.getCardNumber().getSymbol() + card.getCardShape().getName();
    }
}
