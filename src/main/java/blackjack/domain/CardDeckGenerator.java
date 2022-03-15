package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CardDeckGenerator {

    private static final List<Card> CARDS = initializeCardDeckByCardNumber();

    private static LinkedList<Card> initializeCardDeckByCardNumber() {
        LinkedList<Card> cards = new LinkedList<>();
        for (CardPattern cardPattern : CardPattern.values()) {
            addCardByPattern(cards, cardPattern);
        }
        return cards;
    }

    private static void addCardByPattern(LinkedList<Card> cards, CardPattern cardPattern) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardPattern, cardNumber));
        }
    }

    public LinkedList<Card> generate() {
        LinkedList<Card> cards = new LinkedList<>(CARDS);
        Collections.shuffle(cards);
        return cards;
    }
}
