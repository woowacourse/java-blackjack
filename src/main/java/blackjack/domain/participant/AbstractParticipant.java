package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.rule.ScoreRule;
import blackjack.domain.state.State;

import java.util.List;

public abstract class AbstractParticipant implements Participant {
    private final String name;
    private State state;

    public AbstractParticipant(String name, State state) {
        this.name = name;
        this.state = state;
    }

    public abstract boolean isReceivable();

    public abstract boolean handOutCard(Card card);

    public abstract boolean isDealer();

    public abstract List<Card> showInitCards();


    @Override
    public int sumTotalScore(ScoreRule scoreRule) {
        return scoreRule.sumTotalScore(state.getCards().toCardList());
    }

    @Override
    public List<Card> showCards() {
        return state.getCards().toCardList();
    }

    @Override
    public void receiveCard(Card card) {
        state.draw(card);
    }

    @Override
    public void changeState() {
        state = state.changeState();
    }

    @Override
    public void stay() {
        state = state.stay();
    }

    @Override
    public boolean isEnd() {
        return state.isEndState();
    }

    @Override
    public boolean isBust() {
        return state.isBust();
    }

    @Override
    public boolean isBlackJack() {
        return state.isBlackJack();
    }

    final public String getName() {
        return name;
    }

    final public State getState() {
        return state;
    }
}
