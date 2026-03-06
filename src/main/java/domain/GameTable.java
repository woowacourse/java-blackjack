package domain;

import domain.dto.PlayerStatus;
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

    public boolean checkBust(String memberName) {
        return members.checkValue(memberName) > BLACKJACK;
    }

    public List<Card> draw(String memberName, Card card) {
        members.provideCard(memberName, card);
        return members.findCardByName(memberName);
    }

    public List<PlayerStatus> checkPlayerStatuses() {
        return members.getAllPlayerName()
                .stream()
                .map(name -> {
                    List<Card> cards = members.findCardByName(name);
                    int totalValue = members.checkValue(name);
                    return new PlayerStatus(name, cards, totalValue);
                }).toList();
    }
}
