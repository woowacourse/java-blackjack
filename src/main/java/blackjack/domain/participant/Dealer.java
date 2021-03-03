package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;
import java.util.Collections;
import java.util.List;

public class Dealer {

    private static final int LIMIT_SCORE = 17;
    private final Hand hand;
    private final CardDeck cardDeck;

    public Dealer() {
        this.hand = new Hand();
        this.cardDeck = CardDeck.newShuffledDeck();
    }

    public Card giveCard() {
        return cardDeck.draw();
    }

    public void receiveCard(final Card card) {
        this.hand.addCard(card);
    }

    public boolean isOverLimitScore() {
        return getTotalScore() >= LIMIT_SCORE;
    }

    public int getTotalScore(){
        return hand.totalScore();
    }

    public List<Card> getHand(){
        return Collections.unmodifiableList(hand.toList());
    }
}
