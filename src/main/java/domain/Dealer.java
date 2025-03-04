package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    private final CardDeck cardDeck;
    private final List<Card> cards;

    private Dealer(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
        cards = new ArrayList<>();
    }

    public static Dealer of(CardDeck cardDeck) {
        return new Dealer(cardDeck);
    }

    public void passCard(Player player) {
        Card card = cardDeck.popCard();
        player.receive(card);
    }
}
