package second.domain.result;

import second.domain.gamer.Gamer;

public interface ResultTypeStrategy {
    boolean judge(Gamer gamer, Gamer counterGamer);
}
