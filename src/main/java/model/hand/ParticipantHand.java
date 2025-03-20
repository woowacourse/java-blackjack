package model.hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.deck.Card;

public abstract class ParticipantHand {
    private static final int INITIAL_DEAL_CARD_COUNT = 2;

    protected final List<Card> cards;

    protected ParticipantHand() {
        this(new ArrayList<>());
    }

    protected ParticipantHand(final List<Card> cards) {
        this.cards = cards;
    }

    public void add(final Card card) {
        validateDuplication(card);
        cards.add(card);
    }

    private void validateDuplication(final Card card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("중복 카드가 선택되었습니다.");
        }
    }

    public boolean checkBlackJack() {
        return this.cards.size() == INITIAL_DEAL_CARD_COUNT
                && calculateFinalScore().isBlackjack();
    }

    public boolean checkBurst() {
        return calculateDefaultScore().isBurst();
    }

    public boolean checkScoreBelow(final Score upperBound) {
        Score defaultScore = calculateDefaultScore();
        return defaultScore.isLessThan(upperBound) || defaultScore.isSame(upperBound);
    }

    public abstract Score calculateDefaultScore();

    public abstract Score calculateFinalScore();

    public abstract ParticipantHand cloneToSoftHand();


    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
