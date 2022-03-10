package blackjack.view;

import blackjack.domain.human.Dealer;
import blackjack.domain.human.Human;
import blackjack.domain.human.Players;

public class OutputView {

    public static void printInitCardState(Players players, Dealer dealer) {
        System.out.printf("%s와 %s에게 2장의 나누었습니다." + System.lineSeparator(), dealer.getName(), players.getPlayerNames());
    }

    public static void printHumanCardState(Human human) {
        String result = human.getName() + "카드: " + human.getCards().toString();
        System.out.println(result);
    }

    public static void printDealerCardAdded() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }
}
