package domain.participant;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public abstract class Participant {
    private static final int BUST_THRESHOLD = 21;
    private final Name name;
    protected final ParticipantCards participantCards;

    public Participant(Name name) {
        this.name = name;
        participantCards = new ParticipantCards(new ArrayList<>());
    }

    public void receiveCard(Card card) {
        participantCards.addCard(card);
    }

    public abstract boolean isContinueGame();

    public Name getName() {
        return this.name;
    }

    public int getScore() {
        return participantCards.calculateScore();
    }

    public boolean isBust() {
        if (this.getScore() > BUST_THRESHOLD) {
            return true;
        }
        return false;
    }

    public boolean isBlackJack() {
        return participantCards.getCardSize() == 2 && getScore() == 21;
    }

    public List<String> getCardsInfo() {
        return participantCards.getCardsInfo();
    }
}
