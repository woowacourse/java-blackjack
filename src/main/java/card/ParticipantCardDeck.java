package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ParticipantCardDeck {
    private static final int BLACK_JACK = 21;

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

    public boolean isBlackJack(final int cardDeckScore) {
        return cardDeckScore == BLACK_JACK;
    }

    public int calculateScore() {
        int totalScore = calculateTotalScore();
        int aceCounts = countAce();

        return adjustAceScore(aceCounts, totalScore);
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    private int calculateTotalScore() {
        return cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    private int adjustAceScore(final int aceCounts, final int totalScore) {
        int maxAdjustableAces = Math.min(aceCounts, (BLACK_JACK - totalScore) / 10);
        return totalScore + (maxAdjustableAces * 10);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int requestSize() {
        return cards.size();
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
