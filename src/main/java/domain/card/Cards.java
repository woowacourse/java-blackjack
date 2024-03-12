package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        if (sumAllScore().isBust()) {
            throw new IllegalStateException("버스트 상태에서는 카드를 추가할 수 없습니다.");
        }
        cards.add(card);
    }

    public Score sumAllScore() {
        Score score = sum();
        if (hasAce()) {
            return score.plusBonusScore();
        }
        return score;
    }

    private Score sum() {
        return cards.stream()
                .map(Card::getScore)
                .reduce(Score.ZERO, Score::plus);
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}
