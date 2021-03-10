package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.rule.BlackJackScoreRule;
import blackjack.domain.rule.ScoreRule;
import blackjack.domain.state.State;

import java.util.*;

public class Dealer implements Participant {
    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int DRAW_BOUND_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    private final String name;
    private final Cards cards;
    private State state;
    private int money = 0;

    public Dealer(ScoreRule scoreRule, State state) {
        this.name = DEALER_NAME;
        this.cards = new Cards(new ArrayList<>());
        this.state = state.changeState();
    }

    @Override
    public void receiveCard(Card card) {
        state.draw(card);
        state = state.changeState();
    }

    @Override
    public List<Card> showInitCards() {
        return cards.splitCardsFromTo(FROM, TO);
    }

    @Override
    public List<Card> showCards() {
        return cards.toCardList();
    }

    @Override
    public boolean isReceiveCard() {
        int totalScore = cards.getTotalScore(new BlackJackScoreRule());
        return totalScore <= DRAW_BOUND_SCORE;
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
        return true;
    }

    @Override
    public GameResult calculateResult(int enemyScore) {
        return GameResult.valueOf(enemyScore, sumTotalScore());

    }

    @Override
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
}
