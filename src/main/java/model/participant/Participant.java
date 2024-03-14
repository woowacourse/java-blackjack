package model.participant;

import model.card.Card;
import model.card.Cards;
import model.game.HitAction;

public abstract class Participant implements HitAction {

    protected Cards cards;

    protected Participant(Cards cards) {
        this.cards = cards;
    }

    @Override
    public void hitCard(Card card) {
        cards = cards.add(card);
    }

    public int cardsSize() {
        return cards.size();
    }

    public Cards getCards() {
        return cards;
    }
}
