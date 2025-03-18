package domain.participant.state;

import domain.participant.state.hand.Score;
import domain.TrumpCard;
import java.util.List;

public interface State {

    boolean canHit();

    State draw(TrumpCard card);

    List<TrumpCard> retrieveCards();

    Score calculateScore();
}
