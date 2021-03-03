package blackjack.domain;

public class Player extends AbstractPlayer {
    @Override
    public boolean isCanDraw() {
        return getResult() <= BLACKJACK;
    }
}
