package cardGame;

import card.Card;
import card.CardDeck;
import dealer.Dealer;
import java.util.List;

public class BlackJackGame {

    private static final String DEALER_NAME = "딜러";

    private final Dealer dealer;
    private final CardDeck cardDeck;

    public BlackJackGame(CardDeck cardDeck, List<Card> cards) {
        this.cardDeck = cardDeck;
        this.dealer = new Dealer(cards);
    }

}
