package domain.participant;

import java.util.ArrayList;
import java.util.List;

import domain.card.Card;
import domain.hand.Score;

public class User extends Player {

    public User(String name) {
        this(name, new ArrayList<>());
    }

    public User(String name, List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean canHit() {
        return getScore().isLessThan(Score.BLACKJACK);
    }
}