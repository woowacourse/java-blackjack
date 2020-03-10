package blackjack.view;

import blackjack.domain.Card.Card;
import blackjack.domain.Card.Cards;

import java.util.Collection;
import java.util.List;

public class OutputView {

    private static final String DELIMITER = ",";
    public static final String STATUS_INFO_MSG = "딜러와  %s에게 2장의 나누었습니다.";

    public static void printStatus(List<String> names) {
        String players = String.join(DELIMITER, names);
        System.out.println(String.format(STATUS_INFO_MSG, players));
    }

    public static void printCardInfo(String name, Cards cards) {
        String cardInfo = String.join(DELIMITER, cards.getInfo());
        System.out.println(String.format("%s: %s", name, cardInfo));
    }

    public static String getCardInfo(Card card) {
        return Integer.toString(card.getNumber()) + card.getFigure();
    }
}
