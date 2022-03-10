package blackjack.view;

import blackjack.domain.human.Player;

public class OutputView {
    public static void printPlayerCardState(Player player) {
        String result = player.getName() + "카드: " + player.getCards().toString();
        System.out.println(result);
    }
}
