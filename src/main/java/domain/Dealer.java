package domain;

import java.util.HashMap;
import java.util.Map;

public final class Dealer {
    private final Hand hand;
    private final Map<Outcome, Integer> result;

    public Dealer() {
        this.hand = new Hand();
        this.result = new HashMap<>();
        for (Outcome outcome : Outcome.values()) {
            result.put(outcome, 0);
        }
    }

    public void drawCard(Cards cards) {
        hand.addCard(cards.draw());
    }

    public Hand getCardList() {
        return hand;
    }

    public Score getScore() {
        return hand.getScore();
    }

    public int getResult() {
        return hand.getResult();
    }

    public int getCount(Outcome playerOutcome) {
        return result.get(playerOutcome);
    }

    public void addResult(Outcome playerOutcome) {
        result.put(playerOutcome, result.getOrDefault(playerOutcome, 0) + 1);
    }

    public boolean checkBust() {
        return hand.checkBust();
    }
}
