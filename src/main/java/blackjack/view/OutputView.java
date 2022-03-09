package blackjack.view;

import blackjack.domain.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class OutputView {

    private static final String INIT_DISTRIBUTE_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다.";
    public static final String MORE_DEALER_DRAW_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    public static final String SUM_PREFIX = " - 결과: ";

    public static void printInitDistribute(Users users, Dealer dealer) {
        System.out.printf(lineSeparator() + INIT_DISTRIBUTE_FORMAT, String.join(", ", toUserName(users)));
        printDealerData(dealer, 1);
        System.out.print(lineSeparator());
        for (User user : users.getUsers()) {
            printUserData(user);
            System.out.print(lineSeparator());
        }
        System.out.print(lineSeparator());
    }

    public static void printUserData(User user) {
        System.out.printf("%s: %s", user.getName(), getHoldingCards(user.getCards()));
    }

    private static void printDealerData(Dealer dealer, int index) {
        System.out.printf(lineSeparator() + "딜러: %s", getHoldingCards(dealer.getCards().subList(index, dealer.getCards().size())));
    }

    public static void printDealerDraw() {
        System.out.println(lineSeparator() + MORE_DEALER_DRAW_CARD);
    }

    public static void printFinalResult(Users users, Dealer dealer) {
        printDealerData(dealer, 0);
        System.out.print(SUM_PREFIX + dealer.getCardSum() + lineSeparator());
        for (User user : users.getUsers()) {
            printUserData(user);
            System.out.print(SUM_PREFIX + user.getCardSum() + lineSeparator());
        }
    }

    private static String getHoldingCards(List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cards) {
            stringBuilder.append(card.getOriginalNumber())
                    .append(card.getCardType())
                    .append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

    private static List<String> toUserName(Users users) {
        return users.getUsers().stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public static void printLineSeparator() {
        System.out.print(lineSeparator());
    }

    public static void printDealerResult(Map<Result, Integer> count) {
        System.out.println(lineSeparator() + "## 최종 승패");
        System.out.printf("딜러: %d승 %d무 %d패", count.getOrDefault(Result.WIN, 0),
                count.getOrDefault(Result.DRAW, 0), count.getOrDefault(Result.LOSE, 0));
        System.out.print(lineSeparator());
    }

    public static void printUserResult(User user, Result result) {
        System.out.printf("%s: %s", user.getName(), result.getName());
        System.out.print(lineSeparator());
    }
}
