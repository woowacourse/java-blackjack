package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hands {
    private static final int DOWNGRADE_SCORE = 10;

    private final List<Card> hands;
    private final HandsScore handsScore;

    public Hands() {
        this.hands = new ArrayList<>();
        this.handsScore = new HandsScore();
    }

    public void addCard(Card card) {
        handsScore.addScore(card.getScore());
        hands.add(card);
    }

    public boolean downgradeAce() {
        List<Value> values = hands.stream()
                .map(Card::getValue)
                .toList();

        if (values.contains(Value.ACEHIGH)) {
            hands.get(values.indexOf(Value.ACEHIGH))
                    .downgradeAce();
            handsScore.addScore(-DOWNGRADE_SCORE);
            return true;
        }
        return false;
    }

    public boolean isBurst() {
        return handsScore.isBurst();
    }

    public int getHandsScore() {
        return handsScore.getScore();
    }

    public List<Card> getHands() {
        return hands;
    }
}
