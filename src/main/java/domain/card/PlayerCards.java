package domain.card;

import domain.Name;

import java.util.List;

public class PlayerCards extends Cards implements Drawable {

    private static final int MAX_SCORE = 21;
    private final Name name;

    public PlayerCards(Name name, List<Card> cards) {
        super(cards);
        this.name = name;
    }

    @Override
    public boolean canDraw() {
        return sum() <= MAX_SCORE;
    }

    public Name getPlayerName() {
        return name;
    }
}
