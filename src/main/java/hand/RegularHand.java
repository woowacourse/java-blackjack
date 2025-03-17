package hand;

import static result.GameStatus.*;

import card.Card;
import java.util.List;
import participant.value.Score;
import result.GameStatus;

public class RegularHand extends Hand {
    protected RegularHand(List<Card> cards) {
        super(cards);
    }

    @Override
    public GameStatus calculateResultAgainst(Hand other) {
        if(other.isBust()) {
            return WIN;
        }
        if(other.isBlackjack()) {
            return LOSE;
        }
        Score score = Score.from(cards);
        Score otherScore = Score.from(other.cards);
        return score.compare(otherScore);
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
