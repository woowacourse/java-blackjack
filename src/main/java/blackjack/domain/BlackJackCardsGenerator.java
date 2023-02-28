package blackjack.domain;

import static blackjack.domain.Number.values;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackCardsGenerator implements CardsGenerator {

    @Override
    public List<Card> generate() {
        List<Card> cards = createCards();
        Collections.shuffle(cards);
        return cards;
    }

    private static List<Card> createCards() {
        List<Card> cards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            addCards(cards, symbol);
        }
        return cards;
    }

    private static void addCards(List<Card> cards, Symbol symbol) {
        for (Number number : values()) {
            cards.add(new Card(symbol, number));
        }
    }
}
