package model.participant;

import java.util.List;
import model.card.Card;
import model.card.Cards;

public class Player extends Participant {

    private static final int HIT_CONDITION = 22;

    private Player(Name name) {
        this(name, new Cards(List.of()));
    }

    private Player(Name name, Cards cards) {
        super(name, cards);
    }

    public static Player from(String name) {
        return new Player(Name.createPlayerName(name));
    }

    public static Player of(String name, List<Card> cards) {
        return new Player(Name.createPlayerName(name), new Cards(cards));
    }

    @Override
    public boolean isPossibleHit() {
        return score() < HIT_CONDITION;
    }
}
