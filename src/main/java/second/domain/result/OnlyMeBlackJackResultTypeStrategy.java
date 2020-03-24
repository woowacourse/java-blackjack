package second.domain.result;

import second.domain.gamer.Gamer;

public class OnlyMeBlackJackResultTypeStrategy implements ResultTypeStrategy{
    @Override
    public boolean judge(Gamer gamer, Gamer counterGamer) {
        return gamer.isBlackJack() && !counterGamer.isBlackJack();
    }
}
