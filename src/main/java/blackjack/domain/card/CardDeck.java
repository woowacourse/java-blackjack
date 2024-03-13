package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {
    private static final List<Card> INITIAL_CARD_DECK = CardFactory.create();

    private final List<Card> cardDeck;

    public CardDeck() {
        this.cardDeck = new ArrayList<>(INITIAL_CARD_DECK);
    }
}
