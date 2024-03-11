package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void downgradeAce() {
        Optional<Card> aceHighCard = hands.stream()
                .filter(card -> card.getValue() == Value.ACEHIGH)
                .findAny();

        if (aceHighCard.isPresent()) {
            aceHighCard.get().downgradeAce();
            handsScore.addScore(-DOWNGRADE_SCORE);
            return;
        }
        throw new IllegalStateException("다운그레이드 할 수 있는 에이스가 없습니다.");
    }

    public boolean hasHighAce() {
        Optional<Value> aceHigh = hands.stream()
                .map(Card::getValue)
                .filter(value -> value == Value.ACEHIGH)
                .findAny();
        return aceHigh.isPresent();
    }

    public boolean isBurst() {
        return handsScore.isBurst();
    }

    public boolean isBlackJack() {
        return handsScore.isBlackJack();
    }

    public int getHandsScore() {
        return handsScore.getScore();
    }

    public List<Card> getHands() {
        return hands;
    }
}
