package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Bust implements BlackjackGameState {

    private final Cards cards;

    public Bust(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public BlackjackGameState hit(final Card card) {
        return null;
    }

    @Override
    public BlackjackGameState stand() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public List<Card> cards() {
        return null;
    }

    @Override
    public int score() {
        return 0;
    }
}
