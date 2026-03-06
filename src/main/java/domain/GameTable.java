package domain;

import domain.dto.MemberStatus;
import java.util.List;

public class GameTable {

    private static final String DEALER_NAME = "딜러";
    private static final int BLACKJACK = 21;
    private static final int DEALER_DRAW_CONDITION = 16;
    private final Members members;

    public GameTable() {
        this.members = new Members();
    }

    public void joinMember(Member member) {
        members.join(member);
    }

    public boolean checkBust(String memberName) {
        return members.checkValue(memberName) > BLACKJACK;
    }

    public List<Card> draw(String memberName, Card card) {
        members.provideCard(memberName, card);
        return members.findCardByName(memberName);
    }

    public boolean draw(Card card) {
        if (members.checkValue(DEALER_NAME) <= DEALER_DRAW_CONDITION) {
            members.provideCard(DEALER_NAME, card);
            return true;
        }
        return false;
    }

    public List<MemberStatus> checkPlayerStatuses() {
        return members.getAllPlayerName()
                .stream()
                .map(name -> {
                    List<Card> cards = members.findCardByName(name);
                    int totalValue = members.checkValue(name);
                    return new MemberStatus(name, cards, totalValue);
                }).toList();
    }
}
