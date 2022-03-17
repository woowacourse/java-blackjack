package blackjack.domain.participant;

import static blackjack.domain.card.Card.BEST_SCORE;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import java.util.ArrayList;

public abstract class Participant {

    protected final Cards cards;
    private final Name name;

    protected Participant(Name name) {
        this.cards = new Cards(new ArrayList<>());
        this.name = name;
    }

    public abstract boolean isReceivable();

    public int calculateBestScore() {
        return cards.getBestScore();
    }

    public boolean isBlackjack() {
        return cards.getSize() == CardDeck.INITIAL_CARD_COUNT && calculateBestScore() == BEST_SCORE;
    }

    public boolean isBusted() {
        return calculateBestScore() > BEST_SCORE;
    }

    public void receive(Cards cards) {
        this.cards.concat(cards);
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
