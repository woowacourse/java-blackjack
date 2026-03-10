package domain;

import java.util.List;

public abstract class Member {
    protected static final int BUST_CONDITION = 21;

    private final String name;
    private final Hand hand;

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
        return hand.cards();
    }

    public void receiveCard(Card card) {
        hand.appendCard(card);
    }

    public abstract MatchResult compareScoreWith(Member other);

//    public MatchResult isCompareScoreWith(Member member) {
//        int myScore = currentValue();
//        int targetScore = member.currentValue();
//
//        if (myScore > BUST_CONDITION && targetScore > BUST_CONDITION) return handleBothBust();
//        if (targetScore > BUST_CONDITION) return MatchResult.WIN;
//        if (myScore > BUST_CONDITION) return MatchResult.LOSE;
//
//        return calculateResultFromNormalCase(myScore, targetScore);
//    }

    protected MatchResult calculateResultFromNormalCase(int myScore, int targetScore) {
        if (myScore > targetScore) return MatchResult.WIN;
        if (myScore < targetScore) return MatchResult.LOSE;
        return MatchResult.DRAW;
    }
}
