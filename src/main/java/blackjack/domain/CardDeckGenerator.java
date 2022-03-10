package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Clover;
import blackjack.domain.card.Diamond;
import blackjack.domain.card.Heart;
import blackjack.domain.card.Spade;
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
