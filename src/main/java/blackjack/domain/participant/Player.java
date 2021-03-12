package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.rule.BlackJackScoreRule;
import blackjack.domain.rule.ScoreRule;
import blackjack.domain.state.State;

import java.util.List;

public class Player implements Participant {
    private static final int FROM = 0;
    private static final int TO = 2;

    private final String name;
    private State state;
    private int money;

    public Player(String name, State state) {
        this.name = name;
        this.state = state.changeState();
    }

    @Override
    public boolean handOutCard(Card card) {
        if (!isReceiveCard()) return false;

        state.draw(card);
        this.state = state.changeState();
        return true;
    }

    @Override
    public List<Card> showInitCards() {
        return state.getCards().splitCardsFromTo(FROM, TO);
    }

    @Override
    public List<Card> showCards() {
        return state.getCards().toCardList();
    }

    @Override
    public boolean isReceiveCard() {
        return !state.isEndState();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    public void betting(int money) {
        this.money = money;
    }

    @Override
    public State getStatus() {
        return state;
    }

    @Override
    public void stay() {
        state = state.stay();
    }

    public int calculateWinPrize(State enemyState) {
        return (int) (money * state.calculateEarningRate(enemyState));
    }
}
