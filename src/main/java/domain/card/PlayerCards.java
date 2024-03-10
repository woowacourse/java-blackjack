package domain.card;

import domain.player.Name;

import java.util.List;

public class PlayerCards extends Cards implements Drawable {

    private final Name name;

    public PlayerCards(Name name, List<Card> cards) {
        super(cards);
        this.name = name;
    }

    @Override
    public boolean canDraw() {
        return bestSum() <= MAX_SCORE;
    }

    public Name getPlayerName() {
        return name;
    }
}
