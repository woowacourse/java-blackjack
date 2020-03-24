package second.domain.result;

import second.domain.gamer.Gamer;

public class WinResultTypeStrategy implements ResultTypeStrategy {
    @Override
    public boolean judge(Gamer gamer, Gamer counterGamer) {
        return (!gamer.isBust() && counterGamer.isBust()) || gamer.isLargerScoreThan(counterGamer);
    }
}