package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {

    public static List<Card> createShuffledCard() {
        List<Card> createdCard = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            addCards(createdCard, suit);
        }
        Collections.shuffle(createdCard);

        return createdCard;
    }

    private static void addCards(List<Card> createdCard, Suit suit) {
        for (Denomination denomination : Denomination.values()) {
            createdCard.add(Card.of(suit, denomination));
        }
    }
}
