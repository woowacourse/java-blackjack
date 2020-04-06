package domain.result.score;

import domain.gamer.Gamer;

public interface ScoreCalculable {
    Score calculateScore(Gamer gamer);
}
