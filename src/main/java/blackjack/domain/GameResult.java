package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class GameResult {

    private final List<Card> cards;
    private final int sum;

    private GameResult(final List<Card> cards, final int sum) {
        this.cards = cards;
        this.sum = sum;
    }

    public static GameResult from(final Participant participant) {
        return new GameResult(participant.getCards(), participant.getSum());
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public int getSum() {
        return sum;
    }
}
