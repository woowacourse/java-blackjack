package model.participant;

import java.util.List;
import model.card.Card;

public class Player extends Participant {
    private Player(String name) {
        super(name);
    }

    public static Participant of(String input) {
        return new Player(input);
    }

    @Override
    public List<Card> open() {
        return hands.asList();
    }

    @Override
    public boolean beats(Participant participant) {
        if (isBust()) {
            return false;
        }

        if (participant.isBust()) {
            return false;
        }

        return calculateScore() > participant.calculateScore();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + getName() + '\'' +
                "hands=" + hands +
                '}';
    }
}
