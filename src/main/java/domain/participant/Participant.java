package domain.participant;

import domain.blackjack.BlackjackScore;
import domain.blackjack.gamestate.GameState;
import domain.blackjack.gamestate.Start;
import domain.card.Card;
import domain.card.Cards;
import java.util.Objects;

public abstract class Participant {
    protected final ParticipantName participantName;
    protected GameState gameState;

    protected Participant(ParticipantName participantName) {
        this.participantName = participantName;
    }

    public void start(Cards cards) {
        this.gameState = Start.from(cards);
    }

    public void receive(Card card) {
        this.gameState = gameState.receive(card);
    }

    public void receive(Cards cards) {
        for (Card card : cards.getCards()) {
            this.receive(card);
        }
    }

    public BlackjackScore calculateBlackjackScore() {
        Cards cards = gameState.getCards();
        return BlackjackScore.from(cards);
    }

    public boolean isBusted() {
        return calculateBlackjackScore().isGreaterThan(BlackjackScore.getMaxScore());
    }

    public abstract Cards getInitialOpeningCards();

    public abstract boolean isAbleToReceiveCard();

    public int getCurrentCardAmount() {
        Cards cards = gameState.getCards();
        return cards.getCards().size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(participantName, getCards());
    }

    public Cards getCards() {
        return gameState.getCards();
    }

    public String getName() {
        return participantName.getName();
    }
}
