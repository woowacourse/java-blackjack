package domain.card;

import domain.user.Score;

public interface Card {
    Score getScore();

    String getSymbol();

    boolean isAce();
}
