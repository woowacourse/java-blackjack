package blackjack.domain;

import java.util.List;
import java.util.ArrayList;

public class Player {

    private Name name;
    private List<Card> cards;

    public Player(Name name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static Player from(Name name) {
        return new Player(name, new ArrayList());
    }

}
