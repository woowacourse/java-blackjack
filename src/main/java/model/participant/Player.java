package model.participant;

import java.util.List;
import model.card.Card;

public class Player extends Participant {
    private Player(String name) {
        super(name);
    }

    public static Player of(String input) {
        return new Player(input);
    }

    @Override
    public List<Card> open() {
        return hands.asList();
    }

    @Override
    public boolean beats(Participant participant) {
        if (!(participant instanceof Dealer dealer)) {
            throw new IllegalArgumentException("플레이어는 딜러와의 승패만 판정할 수 있습니다.");
        }

        if (isBust()) {
            return false;
        }

        if (isBlackjack()) {
            return true;
        }

        if (dealer.isBust()) {
            return true;
        }

        if (dealer.isBlackjack()) {
            return false;
        }

        return calculateScore() >= participant.calculateScore();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + getName() + '\'' +
                "hands=" + hands +
                '}';
    }
}
