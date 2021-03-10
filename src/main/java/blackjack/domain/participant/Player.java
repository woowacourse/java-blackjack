package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.rule.ScoreRule;

import java.util.ArrayList;
import java.util.List;

public class Player implements Participant {
    private static final int FROM = 0;
    private static final int TO = 2;
    private static final int DRAW_BOUND_SCORE = 21;

    private String name;
    private List<Card> cards;
    private ScoreRule scoreRule;
    private boolean isContinue;

    public Player(String name, ScoreRule scoreRule) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.scoreRule = scoreRule;
        this.isContinue = true;
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

    public boolean isContinue() {
        return isContinue;
    }

    public void endOwnTurn() {
        isContinue = false;
    }
}
