package domain;

import java.util.List;

public abstract class Participant<T extends Participant<T>> {

    protected ParticipantName participantName;
    protected Cards cards;

    public Participant(ParticipantName participantName, Cards cards) {
        this.participantName = participantName;
        this.cards = cards;
    }

    public T drawCard(List<Card> providedCards) {
        return createParticipant(providedCards);
    }

    public boolean isBurst() {
        return cards.isBlackjackScoreExceeded();
    }

    public int getTotalRankSum() {
        return cards.calculateTotalRank();
    }

    protected abstract T createParticipant(List<Card> cards);

    public int calculateDifferenceFromTwentyOne() {
        return cards.calculateDifferenceFromTwentyOne();
    }

    public String getName() {
        return participantName.getName();
    }

    public Cards getCards() {
        return cards;
    }
}
