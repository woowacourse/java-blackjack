package blackjack.domain.card;

import blackjack.domain.card.component.CardFigure;
import blackjack.domain.card.component.CardNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeckFactory {
    private static List<Card> cachedCards;

    static {
        cachedCards = new ArrayList<>();
        for (CardNumber cardNumber : CardNumber.values()) {
            for (CardFigure cardFigure : CardFigure.values()) {
                cachedCards.add(new Card(cardNumber, cardFigure));
            }
        }
    }

    public static CardDeck create() {
        Collections.shuffle(cachedCards);
        return new CardDeck(new Cards(cachedCards));
    }
}
