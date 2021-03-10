package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.rule.ScoreRule;
import blackjack.domain.state.State;

import java.util.ArrayList;
import java.util.List;

public class Player implements Participant {
    private static final int FROM = 0;
    private static final int TO = 2;
    private static final int DRAW_BOUND_SCORE = 21;

    private final String name;
    private State state;
    private int money;

    public Player(String name, int money, State state) {
        this.name = name;
        this.money = money;
        this.state = state;
        state.changeState();
    }

    @Override
    public void receiveCard(Card card) {
        state.draw(card);
        state.changeState();
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
        return true;
    }

    @Override
    public int sumTotalScore() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public GameResult calculateResult(int enemyScore) {
        return GameResult.valueOf(sumTotalScore(), enemyScore);
    }

    @Override
    public void betting(int money) {
        this.money = money;
    }

    @Override
    public State getStatus() {
        return null;
    }

    @Override
    public void stay() {
        state = state.stay();
    }
}
