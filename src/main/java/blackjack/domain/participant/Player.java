package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.rule.ScoreRule;

import java.util.ArrayList;
import java.util.List;

public class Player implements Participant {
    private static final int FROM = 0;
    private static final int TO = 2;
    private static final int DRAW_BOUND_SCORE = 21;

    private final String name;
    private final List<Card> cards;
    private final ScoreRule scoreRule;
    private int money;

    public Player(String name, ScoreRule scoreRule) {
        this.name = name;
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
}
