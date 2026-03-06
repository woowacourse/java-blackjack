package domain;

import java.util.List;

public class GameTable {

    private static final int BLACKJACK = 21;
    private final Members members;

    public GameTable() {
        this.members = new Members();
    }

    public void joinMember(Member member) {
        members.join(member);
    }

    public CurrentResult checkCurrentResult(String memberName) {
        int totalValue = members.checkBust(memberName);
        boolean isBust = totalValue > BLACKJACK;
        return new CurrentResult(isBust, totalValue);
    }

    public List<Card> draw(String memberName, Card card) {
        members.provideCard(memberName, card);
        return members.findCardByName(memberName);
    }
}
