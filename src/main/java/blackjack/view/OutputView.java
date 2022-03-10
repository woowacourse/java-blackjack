package blackjack.view;

import blackjack.domain.human.Player;

public class OutputView {
    public static void printPlayerCardState(Player player) {
        String result = player.getName() + "카드: " + player.getCards().toString();
        System.out.println(result);
    }

    public static void printDealerCardAdded() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
