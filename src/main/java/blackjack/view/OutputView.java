package blackjack.view;

import blackjack.domain.User;
import blackjack.domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String COMMA_WITH_BLANK = ", ";

    public static void printInitialComment(List<User> users) {
        System.out.printf("%s와 %s에게 2장의 카드를 나누어주었습니다.\n", users.get(0).getName(), createUsersStringFormat(users.subList(1, users.size())));
        for (User user : users) {
            printCards(user);
        }
    }

    public static void printCards(User user) {
        System.out.printf("%s 카드 : %s \n", user.getName(), createCardsStringFormat(user.getCards()));
    }

    private static String createCardsStringFormat(List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(COMMA_WITH_BLANK));
    }

    private static String createUsersStringFormat(List<User> users) {
        return users.stream()
                .map(User::getName)
                .collect(Collectors.joining(COMMA_WITH_BLANK));
    }

}
