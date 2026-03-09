package domain.table;

import domain.vo.RoundResult;
import domain.card.Card;
import domain.member.Member;
import domain.member.Members;
import java.util.List;
import java.util.Map;

public class GameTable {

    private static final int BLACKJACK = 21;
    private static final int DEALER_DRAW_CONDITION = 16;

    private final Members members;

    public GameTable(List<String> playerNames) {
        this.members = new Members(playerNames);
    }

    public List<String> getMemberNames() {
        return members.getMemberNames();
    }

    public String getDealerName() {
        return members.getDealerName();
    }

    public void draw(String memberName, Card card) {
        Member member = members.findByName(memberName);
        member.receiveCard(card);
    }

    public boolean canDealerDraw() {
        return members.getValue(getDealerName()) <= DEALER_DRAW_CONDITION;
    }

    public boolean checkBust(String memberName) {
        return members.getValue(memberName) > BLACKJACK;
    }

    public List<Card> getCards(String playerName) {
        return members.findCardByName(playerName);
    }

    public List<Card> getFirstCards(String memberName) {
        return members.getFirstCards(memberName);
    }

    public int memberPoint(String playerName) {
        return members.getValue(playerName);
    }

    public Map<String, RoundResult> checkGameResult() {
        return members.judgeGameResults();
    }
}
