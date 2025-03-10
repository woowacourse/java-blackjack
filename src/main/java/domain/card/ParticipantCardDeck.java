package domain.card;

import domain.game.GameRule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ParticipantCardDeck {
    private final List<Card> cards;

    private ParticipantCardDeck(final List<Card> cards) {
        this.cards = cards;
    }

    public ParticipantCardDeck(final ParticipantCardDeck participantCardDeck) {
        this.cards = new ArrayList<>(participantCardDeck.cards);
    }

    public static ParticipantCardDeck generateEmptySet() {
        return new ParticipantCardDeck(new ArrayList<>());
    }

    public int calculateScore() {
        int totalScore = 0;
        int aceCounts = 0;

        for (Card card : cards) {
            totalScore += card.getNumber();
            if (card.isAceCard()) {
                aceCounts++;
            }
        }

        return adjustAceScore(aceCounts, totalScore);
    }

    private int adjustAceScore(int aceCounts, int totalScore) {
        while (aceCounts-- > 0) {
            if (totalScore + 10 <= GameRule.BUST_THRESHOLD.getValue()) {
                totalScore += 10;
            }
        }
        return totalScore;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParticipantCardDeck participantCardDeck = (ParticipantCardDeck) o;
        return Objects.equals(cards, participantCardDeck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
