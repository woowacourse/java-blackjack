package util;

import domain.Card;

import java.util.List;
import java.util.stream.Collectors;

public class CardStatusConverter {

    public static List<String> convertToCardStatus(List<Card> cards) {
        return cards.stream()
                .map(CardStatusConverter::getCardStatus)
                .collect(Collectors.toList());
    }

    private static String getCardStatus(Card card) {
        return card.getValue().getExpression() + card.getShape().getName();
    }

}
