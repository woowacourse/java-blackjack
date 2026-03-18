package domain.participant;

import domain.Hand;
import domain.card.Card;
import domain.card.Name;
import java.util.List;

public class Player extends Participant {

    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public Player(Name name, Hand hand) {
        super(hand);
        this.name = name;
    }

    public static Player from(String name) {
        return new Player(new Name(name));
    }

    public static Player of(Name name, List<Card> cards) {
        Hand hand = Hand.from(cards);
        return new Player(name, hand);
    }

    public Name getName() {
        return name;
    }

    public String getNameString() {
        return name.value();
    }
}
