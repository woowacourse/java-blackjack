package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int INIT_CARD_COUNT = 2;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Score sumAllCards() {
        Score score = sumScores();
        if (hasAce()) {
            return score.plusBonusScore();
        }
        return score;
    }

    private Score sumScores() {
        return cards.stream()
                .map(Card::getNumber)
                .map(Score::get)
                .reduce(Score::plus)
                .orElseThrow(() -> new IllegalStateException("카드가 존재하지 않습니다."));
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        return cards.size() == INIT_CARD_COUNT && sumAllCards().isBlackjackScore();
    }

    public boolean isBust() {
        return sumAllCards().isBustScore();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}
