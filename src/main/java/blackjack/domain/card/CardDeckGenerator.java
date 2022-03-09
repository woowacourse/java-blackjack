package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;

public class CardDeckGenerator {

    public static CardDeck createCardDeckByCardNumber() {
        LinkedList<Card> cards = new LinkedList<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Diamond(cardNumber));
            cards.add(new Clover(cardNumber));
            cards.add(new Heart(cardNumber));
            cards.add(new Spade(cardNumber));
        }
        return new CardDeck(shuffleCard(cards));
    }

    private static LinkedList<Card> shuffleCard(LinkedList<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }
}
