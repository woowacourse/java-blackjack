package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import java.util.Collections;
import java.util.LinkedList;

public class CardDeckGenerator {

    public static CardDeck createCardDeckByCardNumber() {
        LinkedList<Card> cards = new LinkedList<>();
        for (final CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(CardPattern.DIAMOND, cardNumber));
            cards.add(new Card(CardPattern.HEART, cardNumber));
            cards.add(new Card(CardPattern.CLOVER, cardNumber));
            cards.add(new Card(CardPattern.SPADE, cardNumber));
        }
        return new CardDeck(shuffleCard(cards));
    }

    private static LinkedList<Card> shuffleCard(final LinkedList<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }
}
