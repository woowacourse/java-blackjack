package domain.result;

import domain.gamer.Gamer;
import domain.result.score.ScoreCalculable;

public abstract class GameRule implements ScoreCalculable, ResultDerivable {
    abstract public boolean canDrawMore(Gamer gamer);
}
