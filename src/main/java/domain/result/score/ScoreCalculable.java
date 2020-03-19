package domain.result.score;

import domain.gamer.AbstractGamer;

public interface ScoreCalculable {
    Score calculateScore(AbstractGamer gamer);

    boolean checkCanDrawMore(AbstractGamer gamer);

    boolean isBurst(AbstractGamer gamer);
}
