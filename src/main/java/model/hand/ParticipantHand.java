package model.hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.deck.Card;

public abstract class ParticipantHand {
    private static final int BURST_SCORE_LIMIT = 21;

    protected final List<Card> cards;

    protected ParticipantHand() {
        this(new ArrayList<>());
    }

    protected ParticipantHand(List<Card> cards) {
        this.cards = cards;
    }

    public void add(final Card card) {
        validateDuplication(card);
        cards.add(card);
    }

    private void validateDuplication(Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("중복 카드가 선택되었습니다.");
        }
    }

    public boolean checkBlackJack() {
        return this.cards.size() == 2
                && calculateFinalScore() == 21;
    }

    public boolean checkBurst() {
        return calculateDefaultScore() > BURST_SCORE_LIMIT;
    }

    public boolean checkScoreBelow(final int upperBound) {
        return calculateDefaultScore() <= upperBound;
    }

    public abstract int calculateFinalScore();

    public abstract ParticipantHand cloneToSoftHand();

    protected int calculateDefaultScore() {
        return cards.stream()
                .mapToInt(Card::getCardRankDefaultValue)
                .sum();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
