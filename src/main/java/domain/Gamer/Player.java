package domain.Gamer;

import domain.card.Card;

import java.util.List;

public class Player extends Gamer {
    public Player(String name, List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean isDrawable() { return false; }

    public static boolean isDrawable(YesOrNo yesorNo) {
        return yesorNo.isDrawable();
    }
}
