package blackjack.view;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String INITIAL_CARD_MESSAGE = "%s와 %s에게 2장의 카드를 나누었습니다.";
    private static final String CARD_STATUS_FORMAT = "%s 카드 : %s";
    public static final String DEALER_DREW_A_CARD_MESSAGE = "딜러는 16이하라 1장의 카드를 더 받았습니다.";

    public static void printInitialCards(Dealer dealer, List<Player> players) {
        String names = parseName(players);
        System.out.println(String.format(INITIAL_CARD_MESSAGE, dealer.getName(), names));
        System.out.println(String.format(CARD_STATUS_FORMAT, dealer.getName(), dealer.getInitialCardStatus()));
        for (Player player : players) {
            printPlayerCard(player);
        }
    }

    public static void printPlayerCard(Player player) {
        System.out.println(String.format(CARD_STATUS_FORMAT, player.getName(), player.getCardStatus()));
    }

    private static String parseName(List<Player> players) {
        List<String> names = players.stream().map(Player::getName).collect(Collectors.toList());
        return String.join(",", names);
    }

    public static void printDealerDrewACard() {
        System.out.println(DEALER_DREW_A_CARD_MESSAGE);
    }
}

