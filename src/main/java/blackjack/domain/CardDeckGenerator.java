package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import java.util.Collections;
import java.util.LinkedList;

public class CardDeckGenerator {

    public static CardDeck createCardDeckByCardNumber() {
        LinkedList<Card> cards = new LinkedList<>();
        for (CardPattern cardPattern : CardPattern.values()) {
            addCardByPattern(cards, cardPattern);
        }
        return new CardDeck(shuffleCard(cards));
    }

    private static void addCardByPattern(LinkedList<Card> cards, CardPattern cardPattern) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardPattern, cardNumber));
        }
    }

    private static LinkedList<Card> shuffleCard(LinkedList<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }
}
