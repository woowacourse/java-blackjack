package blackjack.model.participant;

import blackjack.model.ResultState;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardScore;
import blackjack.model.card.HandCard;
import blackjack.model.state.ParticipantState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Participant {
    private static int CURRENT_MAX_ID = 0;
    public static final int BLACKJACK_NUMBER = 21;

    private final int id;
    private final Name name;
    protected ParticipantState currentState;
    protected final HandCard handcard;

    public Participant(Name name, ParticipantState state, HandCard handCard) {
        this.id = CURRENT_MAX_ID;
        this.name = name;
        this.currentState = state;
        this.handcard = handCard;
        CURRENT_MAX_ID++;
    }

    public Participant(Name name, ParticipantState state) {
        this(name, state, new HandCard());
    }

    abstract public ResultState resultState();

    abstract public void draw(CardDeck cardDeck);

    abstract public void changeToStand();

    abstract public Map<String, List<Card>> firstDistributedCard();

    public CardScore cardScore() {
        return handcard.score(resultState());
    }

    public boolean isEqualId(int findId) {
        return this.id == findId;
    }

    public boolean isBlackjack() {
        return currentState.isBlackjack();
    }

    public boolean isBust() {
        return currentState.isBust();
    }

    public boolean isFinished() {
        return currentState.isFinished();
    }

    public List<Card> getCards() {
        return handcard.getCards();
    }

    public Map<String, List<Card>> getCardUnit() {
        Map<String, List<Card>> cardUnit = new HashMap<>();
        cardUnit.put(getName(), getCards());
        return cardUnit;
    }

    public String getName() {
        return name.getName();
    }
}
