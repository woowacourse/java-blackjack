package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.deck.PlayingDeck;

import java.util.List;

public class Dealer extends Gamer {

    private static final int DEALER_HIT_MAX_SCORE = 16;

    private final PlayingDeck playingDeck;

    public Dealer(PlayingDeck playingDeck) {
        this.playingDeck = playingDeck;
    }

    public Dealer(List<Card> cards) {
        this.playingDeck = null;
        cards.forEach(this::receiveCard);
    }

    public Card drawCard() {
        return playingDeck.drawCard();
    }

    public void initialDraw() {
        for (int i = 0; i < 2; i++) {
            receiveCard(drawCard());
        }
    }

    public Card getFirstCard() {
        return getHandDeck().get(0);
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() <= DEALER_HIT_MAX_SCORE;
    }
}
