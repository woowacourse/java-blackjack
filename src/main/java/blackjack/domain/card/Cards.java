package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static Cards instance;
    private final List<Card> cards;

    private Cards() {
        cards = new ArrayList<>();
        Arrays.stream(CardShape.values())
            .forEach(shape -> Arrays.stream(CardNumber.values())
                .forEach(number -> cards.add(Card.valueOf(shape, number))));
        Collections.shuffle(cards);
    }

    public static Cards getInstance() {
        if (instance == null) {
            instance = new Cards();
        }
        return instance;
    }

    public Card draw() {
        return cards.remove(cards.size() - 1);
    }
}