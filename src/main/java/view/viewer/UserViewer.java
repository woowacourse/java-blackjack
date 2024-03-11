package view.viewer;

import domain.card.Card;
import domain.user.User;
import java.util.List;
import java.util.stream.Collectors;
import view.viewer.card.CardViewer;

public class UserViewer {
    public static final String DEALER_NAME_VALUE = "딜러";

    public static String showAllCards(User user) {
        if (user.isPlayer()) {
            return joinNameAndCards(user.getNameValue(), user.getAllCards());
        }
        return joinNameAndCards(DEALER_NAME_VALUE, user.getAllCards());
    }

    public static String showVisibleCards(User user) {
        if (user.isPlayer()) {
            return joinNameAndCards(user.getNameValue(), user.getVisibleCards());
        }
        return joinNameAndCards(DEALER_NAME_VALUE, user.getVisibleCards());
    }

    private static String joinNameAndCards(String name, List<Card> cards) {
        return name + "카드: " + joinCards(cards);
    }

    private static String joinCards(List<Card> cards) {
        return cards.stream()
                .map(CardViewer::show)
                .collect(Collectors.joining(", "));
    }
}
