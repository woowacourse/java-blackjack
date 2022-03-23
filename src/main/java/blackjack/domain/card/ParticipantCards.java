package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParticipantCards {

    private static final int NO_COUNT = 0;
    private static final int BUST_THRESHOLD = 21;
    private static final int CONVERT_ACE_AMOUNT = 10;
    private static final int FIRST_CARD_INDEX = 0;
    private static final int READY_SIZE_THRESHOLD = 2;
    private static final int BLACKJACK_SIZE = 2;
    private static final int BLACKJACK_SCORE = 21;

    private final List<Card> cards;

    public ParticipantCards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public ParticipantCards() {
        this.cards = new ArrayList<>();
    }

    public int calculateScore() {
        int totalScore = cards.stream().mapToInt(Card::getPoint).sum();
        int aceCount = getAceCount();

        if (aceCount != NO_COUNT) {
            return calculateScoreWithAce(aceCount, totalScore);
        }
        return totalScore;
    }

    public ParticipantCards addCard(Card card) {
        cards.add(card);
        return new ParticipantCards(cards);
    }

    public boolean isReady() {
        return cards.size() < READY_SIZE_THRESHOLD;
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_SIZE && calculateScore() == BLACKJACK_SCORE;
    }

    public boolean isBust() {
        return calculateScore() > BUST_THRESHOLD;
    }

    public Card getFirstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    private int getAceCount() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    private int calculateScoreWithAce(int aceCount, int totalScore) {
        while (isNeedToConvertAceValue(aceCount, totalScore)) {
            totalScore = totalScore - CONVERT_ACE_AMOUNT;
            aceCount--;
        }
        return totalScore;
    }

    private boolean isNeedToConvertAceValue(int aceCount, int totalScore) {
        return aceCount > NO_COUNT && totalScore > BUST_THRESHOLD;
    }

}
