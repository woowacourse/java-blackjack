package blackjack.domain.participants;

import static blackjack.domain.GameBoard.INITIAL_CARD_COUNT;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Deck;

public class Dealer extends Player {
    public static final int DEALER_BOUNDARY_SCORE = 17;
    private static final Name dealerName = new Name("딜러");

    private final Deck allCardDeck;

    public Dealer() {
        super(dealerName);
        this.allCardDeck = new Deck();
    }

    public Card drawCard() {
        return allCardDeck.pickRandomCard();
    }

    public void setInitialHand() {
        for (int cardCount = 0; cardCount < INITIAL_CARD_COUNT; cardCount++) {
            receiveCard(drawCard());
        }
    }
}
