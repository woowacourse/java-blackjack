package dealer;

import card.Card;
import card.CardDeck;
import java.util.List;

public class Dealer {

    private final CardDeck cardDeck;

    private List<Card> cards;

    public Dealer(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Card giveCard() {
        cardDeck.shuffleCard();
        return cardDeck.pickCard();
    }
}
