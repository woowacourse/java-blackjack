package domain;

import java.util.List;

public interface State {

    boolean canHit();

    State draw(TrumpCard card);

    List<TrumpCard> retrieveCards();

    Score calculateScore();
}
