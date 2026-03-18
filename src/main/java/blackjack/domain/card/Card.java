package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record Card(
        CardValue cardValue,
        CardShape cardShape
) {

    private static final List<Card> ALL_CARDS = new ArrayList<>();

    static {
        for (CardShape cardShape : CardShape.values()) {
            makeCardsWith(cardShape);
        }
    }

    public static List<Card> getAllCards() {
        return List.copyOf(ALL_CARDS);
    }

    private static void makeCardsWith(CardShape cardShape) {
        Arrays.stream(CardValue.values()).forEach((cardValue) ->
                ALL_CARDS.add(new Card(cardValue, cardShape)));
    }

    public boolean isAce() {
        return cardValue.isAce();
    }

    public String getName() {
        return cardValue.getName() + cardShape.getName();
    }

    public int getCardValue() {
        return cardValue.getValue();
    }

}
