package domain.player;

import domain.AnswerType;
import domain.card.Card;
import domain.card.Cards;

public class User extends Player {
    public User(String name, Card... cards) {
        super(name, cards);
    }

    public void insertCard(Cards cards, AnswerType answerType) {
        if (AnswerType.YES.equals(answerType)) {
            insertCard(cards);
        }
    }

    @Override
    public void insertCard(Cards cards) {
        this.cards.add(cards.pop());
    }
}
