package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Participant {

    protected final Cards cards;

    public Participant(Cards cards) {
        this.cards = cards;
    }

    public abstract boolean isReceivable();

    public abstract int calculateBestScore();

    public void receive(Card card){
        cards.receive(card);
    }
}
