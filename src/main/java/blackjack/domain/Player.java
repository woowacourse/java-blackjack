package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public Player(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public boolean isDrawable() {
        return calculateScore() <= BLACKJACK_SCORE;
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::getMaxScore)
                .sum();

        //TODO 인덴트 해결하기
        for (Card card : cards) {
            if (score <= BLACKJACK_SCORE) {
                return score;
            }
            score = score + card.getMinScore() - card.getMaxScore();
        }
        return score;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void add(Card card) {
        if (!isDrawable()) {
            throw new IllegalStateException("더 이상 카드를 추가할 수 없습니다.");
        }
        cards.add(card);
    }
}
