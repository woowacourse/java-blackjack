package ui;

import domain.Card;
import domain.user.AbstractUser;
import domain.user.Dealer;
import domain.user.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printCardsStatus(Dealer dealer, List<Player> players) {
        List<String> nameValues = players.stream()
                .map(AbstractUser::getNameValue)
                .collect(Collectors.toList());
        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.", String.join(", ", nameValues));
        System.out.println();
        printCardsStatusOfUser(dealer);
        players.forEach(OutputView::printCardsStatusOfUser);
    }

    public static void printCardsStatusOfUser(AbstractUser user) {
        List<String> cardTexts = user.getCards().stream()
                .map(Card::getText)
                .collect(Collectors.toList());
        System.out.printf("%s: %s", user.getNameValue(), String.join(", ", cardTexts));
        System.out.println();
    }
}
