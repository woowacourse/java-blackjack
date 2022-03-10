package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import java.util.ArrayList;

public abstract class Participant {

    protected final Cards cards;

    public Participant() {
        this.cards = new Cards(new ArrayList<>());
    }

    public abstract boolean isReceivable();

    public abstract int calculateBestScore();

    public void receive(Cards cards){
        this.cards.concat(cards);
    }
}
