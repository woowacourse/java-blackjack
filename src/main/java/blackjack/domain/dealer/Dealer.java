package blackjack.domain.dealer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.hand.Hand;
import blackjack.domain.hand.Score;

public class Dealer {

    private final Hand hand;

    public Dealer() {
        this(new Hand());
    }

    public Dealer(Hand hand) {
        this.hand = hand;
    }

    public void deal(CardDeck cardDeck) {
        hand.appendInitial(cardDeck);
    }

    public void draw(CardDeck cardDeck) {
        if (canHit()) {
            hand.append(cardDeck.popCard());
        }
    }

    public boolean canHit() {
        return hand.calculateHandScore().isDealerHit();
    }

    public Score handScore() {
        return hand.calculateHandScore();
    }

    public boolean isNotBlackjack() {
        return !hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public Card revealFirstCard() {
        return hand.getCards().get(0);
    }

    public Hand getHand() {
        return hand;
    }
}
