package blackjack.domain.state;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Running implements BlackjackGameState {

    private final List<Card> cards;

    public Running(final List<Card> cards) {
        Objects.requireNonNull(cards, "cards는 null이 들어올 수 없습니다.");
        this.cards = new ArrayList<>(cards);
    }

    @Override
    public BlackjackGameState hit() {
        return null;
    }

    @Override
    public BlackjackGameState stay() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double earningRate(final BlackjackGameState blackjackGameState) {
        return 0;
    }
}
