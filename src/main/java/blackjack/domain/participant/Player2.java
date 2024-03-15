package blackjack.domain.participant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Hand;
import blackjack.domain.game.Score;
import java.util.Objects;

public class Player2 {

    private static final int REVEAL_COUNT = 2;

    private final Name name;
    private final Hand hand;

    public Player2(Name name) {
        this(name, new Hand());
    }

    public Player2(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void deal(CardDeck cardDeck) {
        hand.appendInitial(cardDeck);
    }

    public Hand revealHand() {
        return hand.revealHand(REVEAL_COUNT);
    }

    public void draw(CardDeck cardDeck) {
        if (canHit()) {
            hand.append(cardDeck.popCard());
        }
    }

    public boolean canHit() {
        return hand.isPlayerHit();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public Score handScore() {
        return hand.calculateHandScore();
    }

    public Name getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player2 player2 = (Player2) o;
        return Objects.equals(name, player2.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name.value();
    }
}
