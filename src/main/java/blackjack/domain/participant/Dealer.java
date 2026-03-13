package blackjack.domain.participant;

import blackjack.domain.GameScore;
import blackjack.domain.card.Card;
import java.util.List;

public class Dealer extends Participant {

    private static final String NAME_VALUE = "딜러";
    private static final int STAND_SCORE = 17;

    public Dealer(Hand hand) {
        super(NAME_VALUE, hand);
    }

    @Override
    public List<Card> getInitialCards() {
        return List.of(hand.getFirstCard());
    }

    public boolean isStand() {
        GameScore standScore = new GameScore(STAND_SCORE);

        return getScore().isBiggerThan(standScore)
                || getScore().equals(standScore);
    }
}
