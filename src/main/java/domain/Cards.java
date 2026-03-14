package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Cards implements Iterable<Card> {
    private static final int BUST_CRITERIA = 21;
    private static final int ACE_EXTRA_SCORE = 10;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        if (calculateCardScoreSum() >= BUST_CRITERIA) {
            throw new IllegalStateException("이미 카드 합계가 21 이상입니다. 카드를 추가할 수 없습니다.");
        }
        cards.add(card);
    }

    public int calculateCardScoreSum() {
        int scoreSum = getScoreSumWithBasicAceScore();
        if (hasAce() && canApplyMaxAceScore(scoreSum)) {
            scoreSum += ACE_EXTRA_SCORE;
        }
        return scoreSum;
    }

    private int getScoreSumWithBasicAceScore() {
        return cards.stream().mapToInt(Card::getCardScore).sum();
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    private boolean canApplyMaxAceScore(int scoreSum) {
        return scoreSum + ACE_EXTRA_SCORE <= BUST_CRITERIA;
    }

    public boolean isLessThanMaxScore() {
        return calculateCardScoreSum() < BUST_CRITERIA;
    }

    public boolean isBust() {
        return calculateCardScoreSum() > BUST_CRITERIA;
    }

    @Override
    public Iterator<Card> iterator() {
        return Collections.unmodifiableList(cards).iterator();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
