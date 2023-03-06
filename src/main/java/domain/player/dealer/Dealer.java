package domain.player.dealer;

import domain.Score;
import domain.area.CardArea;
import domain.card.Card;
import domain.player.Name;
import domain.player.Player;

public class Dealer extends Player {

    private static final int DEALER_LIMIT_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean canHit() {
        return score().isLessEqualThan(new Score(DEALER_LIMIT_SCORE));
    }

    public Card faceUpFirstCard() {
        return cardArea.firstCard();
    }
}
