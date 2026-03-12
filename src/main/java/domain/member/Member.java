package domain.member;

import domain.MatchResult;
import domain.card.Card;
import java.util.List;

public abstract class Member {
    protected static final int BUST_CONDITION = 21;

    private final String name;
    private Hand hand;

    public Member(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String name() {
        return name;
    }

    public int currentValue() {
        return hand.calculateTotalValue();
    }

    public List<Card> currentCards() {
        return hand.getAllCard();
    }

    public void receiveCard(Card card) {
        hand = hand.appendCard(card);
    }

    public abstract MatchResult compareScoreWith(Member other);

    protected MatchResult calculateResultFromNormalCase(int myScore, int targetScore) {
        if (myScore > targetScore) {
            return MatchResult.WIN;
        }
        if (myScore < targetScore) {
            return MatchResult.LOSE;
        }
        return MatchResult.DRAW;
    }
}
