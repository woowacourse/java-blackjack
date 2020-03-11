package domain;

import java.util.List;

public class User extends Player {
    public User(Card... cards) {
        super(cards);
    }

    @Override
    void insertCard(Cards cards) {
        this.cards.add(cards.pop());
    }

    public void insertCard(Cards cards, AnswerType answerType) {
        if(AnswerType.YES.equals(answerType)){
            insertCard(cards);
        }
    }
}
