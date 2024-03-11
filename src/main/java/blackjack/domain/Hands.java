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
    // TODO boolean의 의미가 직관적이지 않음
    // TODO 메서드명만보면 void 반환형 같아 보임
    // TODO 특별한 메서드를 호출하는데 있어 제약사항이 없음
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
