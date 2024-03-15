package model.participant;

import model.card.Card;
import model.card.Cards;
import model.game.HitAction;

public abstract class Participant implements HitAction {

    protected Name name;
    protected Cards cards;

    protected Participant(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    @Override
    public void hitCard(Card card) {
        cards = cards.add(card);
    }

    public int cardsSize() {
        return cards.size();
    }

    public String getName() {
        return name.getValue();
    }

    public Cards getCards() {
        return cards;
    }
}
