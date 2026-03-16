package blackjack.domain.participant;

import blackjack.domain.GameScore;
import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    private static final String NAME_VALUE = "딜러";
    private static final GameScore STAND_SCORE = new GameScore(17);


    public Dealer(Hand hand) {
        super(NAME_VALUE, hand);
    }

    @Override
    public List<Card> getInitialCards() {
        return List.of(hand.getFirstCard());
    }

    public boolean isStand() {
        return getScore().isBiggerThan(STAND_SCORE)
                || getScore().equals(STAND_SCORE);
    }
}
