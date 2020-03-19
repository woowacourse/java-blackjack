package domain.result;

import domain.gamer.AbstractGamer;
import domain.result.score.ScoreCalculable;

public abstract class GameRule implements ScoreCalculable, ResultDerivable {
    abstract public boolean canDrawMore(AbstractGamer gamer);
}
