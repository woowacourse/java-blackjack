package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements Participant {
    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int DRAW_BOUND_SCORE = 16;

    private final String name;
    private final ArrayList<Card> cards;
    private final ScoreRule scoreRule;

    public Dealer(String name, ArrayList<Card> cards, ScoreRule scoreRule) {
        this.name = name;
        this.cards = cards;
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
}
