package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.User;
import blackjack.domain.Users;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class OutputView {

    private static final String INIT_DISTRIBUTE_FORMAT = "딜러와 %s에게 2장의 카드를 나누었습니다.\n";

    public static void printInitDistribute(Users users, Dealer dealer) {
        System.out.printf(lineSeparator() + INIT_DISTRIBUTE_FORMAT, String.join(", ", toUserName(users)));
        printDealerData(dealer);
        for (User user : users.getUsers()) {
            printUserData(user);
        }
        System.out.print(lineSeparator());
    }

    private static List<String> toUserName(Users users) {
        return users.getUsers().stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    public static void printUserData(User user) {
        System.out.printf("%s: %s", user.getName(), getHoldingCards(user.getCards()));
    }

    public static void printDealerData(Dealer dealer) {
        System.out.printf("딜러: %s", getHoldingCards(dealer.getCards().subList(1, dealer.getCards().size())));
    }

    private static String getHoldingCards(List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cards) {
            stringBuilder.append(card.getOriginalNumber())
                    .append(card.getCardType())
                    .append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(lineSeparator());
        return stringBuilder.toString();
    }
}
