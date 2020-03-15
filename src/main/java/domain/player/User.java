package domain.player;

import domain.Answer;
import domain.card.Card;
import domain.card.Cards;

public class User extends Player {
    public User(String name, Card... cards) {
        super(name, cards);
    }

    public void hitCard(Cards cards, Answer answerType) {
        if (answerType.isYes()) {
            hitCard(cards);
        }
    }

    @Override
    public void hitCard(Cards cards) {
        this.cards.add(cards.hit());
    }
}
