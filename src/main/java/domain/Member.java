package domain;

import java.util.List;

public class Member {

    private static final int BUST_CONDITION = 21;

    private final String name;
    private final Hand hand;
    private final Role role;

    public Member(String name, Role role) {
        this.name = name;
        this.role = role;
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

    public boolean isDealer() {
        return role.equals(Role.DEALER);
    }

    public void receiveCard(Card card) {
        hand.appendCard(card);
    }

    public MatchResult isCompareScoreWith(Member member) {
        int myScore = currentValue();
        int targetScore = member.currentValue();

        if (myScore > BUST_CONDITION && targetScore > BUST_CONDITION) return handleBothBust();
        if (targetScore > BUST_CONDITION) return MatchResult.WIN;
        if (myScore > BUST_CONDITION) return MatchResult.LOSE;

        return calculateResultFromNormalCase(myScore, targetScore);
    }

    private MatchResult handleBothBust() {
        if (isDealer()) return MatchResult.WIN;
        return MatchResult.LOSE;
    }

    private MatchResult calculateResultFromNormalCase(int myScore, int targetScore) {
        if (myScore > targetScore) return MatchResult.WIN;
        if (myScore < targetScore) return MatchResult.LOSE;
        return MatchResult.DRAW;
    }
}
