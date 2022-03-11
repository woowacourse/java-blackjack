package blackjack.domain.participant;

import java.util.LinkedHashSet;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.card.PlayStatus;

public abstract class Participant {

    protected final Cards cards;
    protected PlayStatus playStatus;

    public Participant() {
        this.cards = new Cards(new LinkedHashSet<>());
        this.playStatus = PlayStatus.HIT;
    }

    public void init(CardDeck cardDeck) {
        cards.add(cardDeck.drawCard());
        cards.add(cardDeck.drawCard());
    }

    public PlayStatus getStatus() {
        return playStatus;
    }

    public boolean isHit() {
        return playStatus == PlayStatus.HIT;
    }

    public int getScore() {
        return cards.sum();
    }

    public void hit(CardDeck cardDeck) {
        cards.add(cardDeck.drawCard());
        updateStatus();
    }

    private void updateStatus() {
        if (cards.getStatus() == PlayStatus.BUST) {
            playStatus = PlayStatus.BUST;
        }
    }

    public abstract String getName();

    public Cards getCards() {
        return cards;
    }
}
