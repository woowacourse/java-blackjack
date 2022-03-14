package blackjack.domain.participant;

import static blackjack.domain.PlayStatus.*;

import java.util.Set;

import blackjack.domain.PlayStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;

public abstract class Participant {

    protected final Cards cards;
    protected PlayStatus playStatus;

    Participant() {
        this.cards = new Cards(Set.of());
        this.playStatus = HIT;
    }

    public void init(CardDeck cardDeck) {
        cards.add(cardDeck.drawCard());
        cards.add(cardDeck.drawCard());
    }

    PlayStatus getStatus() {
        return playStatus;
    }

    public boolean isHit() {
        return playStatus == HIT;
    }

    public int getScore() {
        return cards.sum();
    }

    public void hit(Card card) {
        cards.add(card);
        playStatus = cards.getStatus();
    }

    public abstract String getName();

    public Set<Card> getCards() {
        return cards.getValue();
    }
}
