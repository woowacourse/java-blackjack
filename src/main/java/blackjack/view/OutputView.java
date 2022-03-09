package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.User;
import blackjack.domain.Users;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String INIT_DISTRIBUTE_FORMAT = "딜러와 %s에게 2장의 나누었습니다.\n";

    public static void printInitDistribute(Users users, Dealer dealer) {
        System.out.printf(INIT_DISTRIBUTE_FORMAT, String.join(", ", toUserName(users)));
        for (User user : users.getUsers()) {
            printUserData(user);
        }
    }

    private static List<String> toUserName(Users users) {
        return users.getUsers().stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    private static void printUserData(User user) {
        System.out.printf("%s: %s", user.getName(), getUserCards(user.getCards()));
    }

    private static String getUserCards(List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : cards) {
            stringBuilder.append(card.getOriginalNumber())
                    .append(card.getCardType())
                    .append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
