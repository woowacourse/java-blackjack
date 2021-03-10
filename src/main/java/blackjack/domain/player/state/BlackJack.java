package blackjack.domain.player.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

public class BlackJack implements State {

    protected BlackJack() {}

    @Override
    public boolean drawable() {
        return false;
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Score score() {
        return Score.ofBlackJack();
    }

    @Override
    public int winningMoney(int batMoney) {
        return (int) (batMoney * 1.5);
    }
}
