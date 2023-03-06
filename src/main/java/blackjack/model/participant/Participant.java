package blackjack.model.participant;

import blackjack.model.ResultState;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardScore;
import blackjack.model.state.State;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Participant {
    private static int CURRENT_MAX_ID = 0;

    private final int id;
    protected Name name;
    protected State currentState;

    public Participant(Name name, State currentState) {
        this.id = CURRENT_MAX_ID;
        this.name = name;
        this.currentState = currentState;
        CURRENT_MAX_ID++;
    }

    abstract public void draw(CardDeck cardDeck);

    abstract public void changeToStand();

    abstract public ResultState resultState();

    abstract public List<Card> firstDistributedCard();

    public Map<String, List<Card>> handCards() {
        Map<String, List<Card>> handCards = new HashMap<>();

        handCards.put(getName(), getCards());
        return handCards;
    }

    public CardScore cardScore() {
        return currentState.getScore(resultState());
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
        return currentState.getHand();
    }

    public int getScore() {
        if (isBust() || cardScore().bigScore() > 21) {
            return cardScore().smallScore();
        }
        return cardScore().bigScore();
    }

    public String getName() {
        return name.getName();
    }
}


