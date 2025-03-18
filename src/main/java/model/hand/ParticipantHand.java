package model.hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.deck.Card;

public abstract class ParticipantHand {
    private static final int BLACKJACK_LIMIT = 21;
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
                && calculateFinalScore() == BLACKJACK_LIMIT;
    }

    public boolean checkBurst() {
        return calculateDefaultScore() > BLACKJACK_LIMIT;
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
