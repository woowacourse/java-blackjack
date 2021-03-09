package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.rule.ScoreRule;
import java.util.*;

public class Dealer implements Participant {
    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int DRAW_BOUND_SCORE = 16;
    private static final String DEALER_NAME = "딜러";

    private final String name;
    private final List<Card> cards;
    private final ScoreRule scoreRule;
    private int money = 0;

    public Dealer(ScoreRule scoreRule) {
        this.name = DEALER_NAME;
        this.cards = new ArrayList<>();
        this.scoreRule = scoreRule;
    }

    @Override
    public void receiveCard(Card card) {
        cards.add(card);
    }

    @Override
    public List<Card> showInitCards() {
        return cards.subList(FROM, TO);
    }

    @Override
    public List<Card> showCards() {
        return cards;
    }

    @Override
    public boolean isReceiveCard() {
        int totalScore = scoreRule.sumTotalScore(cards);
        return totalScore <= DRAW_BOUND_SCORE;
    }

    @Override
    public int sumTotalScore() {
        return scoreRule.sumTotalScore(cards);
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
}
