package domain.participant;

import domain.blackjack.BlackjackScore;
import domain.blackjack.gamestate.GameState;
import domain.blackjack.gamestate.Playing;
import domain.card.Card;
import domain.card.Cards;
import java.util.Objects;

public abstract class Participant {
    private static final int INITIAL_CARD_AMOUNT = 2;

    protected final ParticipantName participantName;
    protected GameState gameState;

    protected Participant(ParticipantName participantName) {
        this.participantName = participantName;
    }

    public void startWith(Cards cards) {
        this.gameState = Playing.from(cards);
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
        return calculateBlackjackScore().isBusted();
    }

    public abstract Cards getInitialOpeningCards();

    public abstract boolean isAbleToReceiveCard();

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

    public int getAdditionalCardsAmount() {
        Cards cards = gameState.getCards();
        return cards.getCards().size() - INITIAL_CARD_AMOUNT;
    }
}
