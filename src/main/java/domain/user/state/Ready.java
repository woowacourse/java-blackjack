package domain.user.state;

import domain.card.Card;

public class Ready extends State {

    protected Ready() {
        super();
    }

    @Override
    public State draw(Card card) {
        cards.addCard(card);
        if (cards.isMaxScore()) {
            return new BlackJack(cards);
        }
        if (cards.isInitCompleted()) {
            return new Running(cards);
        }
        return this;
    }
}
