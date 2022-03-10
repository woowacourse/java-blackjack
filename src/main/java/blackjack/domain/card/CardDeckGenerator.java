package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeckGenerator {

    public static CardDeck generate() {
        List<Card> cards = new ArrayList<>();
        for (Denomination denomination : Denomination.values()) {
            generateCard(cards, denomination);
        }
        Collections.shuffle(cards);
        return CardDeck.generate(cards);
    }

    private static void generateCard(List<Card> cards, Denomination denomination) {
        for (Symbol symbol : Symbol.values()) {
            cards.add(Card.of(denomination, symbol));
        }
    }
}
