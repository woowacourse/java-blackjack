package domain.participant;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;
import java.util.Objects;

public abstract class Participant {

    protected ParticipantName participantName;
    protected Cards cards;

    public Participant(ParticipantName participantName, Cards cards) {
        this.participantName = participantName;
        this.cards = cards;
    }

    public abstract boolean isDealer();

    public void drawCard(List<Card> providedCards) {
        cards = cards.addCards(providedCards);
    }

    public boolean isBust() {
        return cards.isBlackjackScoreExceeded();
    }

    public int getTotalRankSum() {
        return cards.calculateTotalRank();
    }

    public boolean shouldHit() {
        return false;
    }

    public abstract List<Card> getInitialCards();

    public int calculateDifferenceFromBlackjackScore() {
        return cards.calculateDifferenceFromBlackjackScore();
    }

    public String getParticipantName() {
        return participantName.getName();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Participant that = (Participant) object;
        return Objects.equals(participantName, that.participantName) && Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participantName, cards);
    }
}
