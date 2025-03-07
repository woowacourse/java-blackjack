package domain;

import domain.card.Cards;

public interface GameRule {
    boolean isBurst(Cards cards);

    int getScore(Cards cards);

    GameResult getResult(Cards self, Cards other);
}
