package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {

    public Dealer(String name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        int score = super.score();
        return score <= 16;
    }

    public Card firstCard() {
        return cards().getFirst();
    }
}
