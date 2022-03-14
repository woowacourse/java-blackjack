package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeckGenerator {

    public static CardDeck generate() {
        List<Card> cards = createCards();
        Collections.shuffle(cards);
        return CardDeck.generate(cards);
    }

    private static List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        createCardByDenomination(cards);
        return cards;
    }

    private static void createCardByDenomination(List<Card> cards) {
        for (Denomination denomination : Denomination.values()) {
            createCardBySymbol(cards, denomination);
        }
    }

    private static void createCardBySymbol(List<Card> cards, Denomination denomination) {
        for (Symbol symbol : Symbol.values()) {
            cards.add(Card.of(denomination, symbol));
        }
    }
}
