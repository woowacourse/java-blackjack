package blackjack.domain.participant;

import static blackjack.domain.card.PlayStatus.*;

import java.util.Set;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.card.PlayStatus;

public abstract class Participant {

    protected final Cards cards;
    protected PlayStatus playStatus;

    public Participant() {
        this.cards = new Cards(Set.of());
        this.playStatus = HIT;
    }

    public void init(CardDeck cardDeck) {
        cards.add(cardDeck.drawCard());
        cards.add(cardDeck.drawCard());
    }

    public PlayStatus getStatus() {
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
