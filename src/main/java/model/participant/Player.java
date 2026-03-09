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
    public List<String> open() {
        return hands.stream()
                .map(Card::toString)
                .toList();
    }
}
