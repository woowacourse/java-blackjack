package domain.participant;

import domain.card.Card;
import domain.card.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParticipantCard {

    private static final int BLACKJACK_SIZE = 2;
    private static final Score ACE_HIGH_SCORE = Score.create(10);
    private static final Score BLACKJACK_SCORE = Score.create(21);

    private final List<Card> cards;

    private ParticipantCard() {
        this.cards = new ArrayList<>();
    }

    public static ParticipantCard create() {
        return new ParticipantCard();
    }

    void addCard(final Card card) {
        cards.add(card);
    }

    Score calculateScore() {
        int aceCount = getAceCount();
        Score score = sumCards();

        while (aceCount > 0 &&
                BLACKJACK_SCORE.isGreaterThanAndEqual(score.add(ACE_HIGH_SCORE))) {
            score = score.add(ACE_HIGH_SCORE);
            aceCount--;
        }
        return score;
    }

    boolean isBust() {
        return calculateScore().isGreaterThan(BLACKJACK_SCORE);
    }

    boolean isBlackJack() {
        return cards.size() == BLACKJACK_SIZE && calculateScore().equals(BLACKJACK_SCORE);
    }

    private Score sumCards() {
        return cards.stream()
                .map(Card::findCardScore)
                .reduce(Score.create(0), Score::add);
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof ParticipantCard)) {
            return false;
        }
        ParticipantCard targetCard = (ParticipantCard) target;
        return Objects.equals(cards, targetCard.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    List<Card> getCards() {
        return List.copyOf(cards);
    }
}
