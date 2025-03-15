package domain.participant;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private static final int INITIAL_CARDS_SIZE = 2;

    protected ParticipantName participantName;
    protected Cards cards;

    public Participant(ParticipantName participantName, Cards cards) {
        this.participantName = participantName;
        this.cards = cards;
    }

    public void drawCard(List<Card> providedCards) {
        cards = cards.addCards(providedCards);
    }

    public abstract boolean shouldHit();

    public boolean isBust() {
        return cards.isBlackjackScoreExceeded();
    }

    public int getTotalRankSum() {
        return cards.calculateTotalRank();
    }

    public boolean isBlackjack() {
        return getCards().size() == INITIAL_CARDS_SIZE && cards.equalToBlackjackScore();
    }

    public String getParticipantName() {
        return participantName.getName();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getBettingAmount() {
        return 0;
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
