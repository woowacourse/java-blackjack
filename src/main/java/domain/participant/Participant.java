package domain.participant;

import domain.blackjack.BlackjackScore;
import domain.blackjack.gamestate.GameState;
import domain.blackjack.gamestate.Playing;
import domain.card.Card;
import domain.card.Cards;
import java.util.Objects;

public abstract class Participant {

    protected final ParticipantName participantName;
    protected GameState gameState;

    protected Participant(ParticipantName participantName) {
        this.participantName = participantName;
    }

    public void startWith(Cards cards) {
        this.gameState = Playing.from(cards);
    }

    public void receive(Card card) {
        if (gameState.isAbleToReceiveCard()) {
            this.gameState = gameState.receive(card);
            return;
        }

        throw new IllegalStateException();
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
        return calculateBlackjackScore().isBusted();
    }

    public abstract Cards getInitialOpeningCards();

    public abstract boolean isAbleToReceiveCard();

    @Override
    public int hashCode() {
        return Objects.hash(participantName, getCards());
    }

    public int getAdditionalCardsAmount() {
        return gameState.getAdditionalCardsAmount();
    }

    public Cards getCards() {
        return gameState.getCards();
    }

    public String getName() {
        return participantName.getName();
    }
}
