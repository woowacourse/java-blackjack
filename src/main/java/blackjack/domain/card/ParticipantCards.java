package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParticipantCards {

    private static final int INITIAL_CARD_SIZE = 2;
    private static final int BLACKJACK_THRESHOLD_NUMBER = 21;
    private static final int ACE_SCORE_DIFFERENCE = 10;
    private static final String CARDS_EMPTY_ERROR_MESSAGE = "초기 카드 세팅이 되어있지 않습니다.";
    private static final String CARDS_NOT_EMPTY_ERROR_MESSAGE = "초기 카드는 카드가 없는 상태에서만 받을 수 있습니다.";

    private final List<Card> cards;

    public ParticipantCards() {
        this.cards = new ArrayList<>();
    }

    public void addInitialCards(List<Card> cards) {
        validateEmpty();
        this.cards.addAll(cards);
    }

    private void validateEmpty() {
        if (!cards.isEmpty()) {
            throw new IllegalArgumentException(CARDS_NOT_EMPTY_ERROR_MESSAGE);
        }
    }

    public boolean isBlackjack() {
        return cards.size() == INITIAL_CARD_SIZE && getScore() == BLACKJACK_THRESHOLD_NUMBER;
    }

    public void addCard(Card card) {
        validateCardsSize();
        cards.add(card);
    }

    public boolean isBust() {
        return getScore() > BLACKJACK_THRESHOLD_NUMBER;
    }

    public int getScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getPoint();
        }
        return calculateScoreAdvantageousWithAce(score);
    }

    private int calculateScoreAdvantageousWithAce(int score) {
        if (isExistAce() && isNotOverThresholdWithConvertedAcePoint(score)) {
            score += ACE_SCORE_DIFFERENCE;
        }
        return score;
    }

    private boolean isExistAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    private boolean isNotOverThresholdWithConvertedAcePoint(int score) {
        return score + ACE_SCORE_DIFFERENCE <= BLACKJACK_THRESHOLD_NUMBER;
    }

    public Card getFirstCard() {
        validateCardsSize();
        return cards.get(0);
    }

    private void validateCardsSize() {
        if (cards.isEmpty()) {
            throw new IndexOutOfBoundsException(CARDS_EMPTY_ERROR_MESSAGE);
        }
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
