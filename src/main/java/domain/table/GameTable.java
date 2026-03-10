package domain.table;

import domain.vo.RoundResult;
import domain.card.Card;
import domain.member.Members;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class GameTable {

    private static final int BLACKJACK = 21;
    private static final int DEALER_DRAW_CONDITION = 16;

    private final Members members;

    public GameTable(Map<String, Integer> players) {
        this.members = new Members(players);
    }

    public void draw(String memberName, Card card) {
        members.draw(memberName, card);
    }

    public boolean canDealerDraw() {
        return members.getValue(getDealerName()) <= DEALER_DRAW_CONDITION;
    }

    public boolean checkBust(String memberName) {
        return members.getValue(memberName) > BLACKJACK;
    }

    public boolean checkBlackjack() {
        return members.getMemberNames()
                .stream()
                .filter(memberName -> !members.getDealerName().equals(memberName))
                .anyMatch(memberName -> members.getValue(memberName) == BLACKJACK);
    }

    public void applyBlackjackBonus() {
        members.getMemberNames()
                .stream()
                .filter(memberName -> !members.getDealerName().equals(memberName))
                .forEach(members::applyBlackjackBonus);
    }

    public Map<String, Integer> checkGameResult() {
        Map<String, Integer> result = new LinkedHashMap<>();
        for (Entry<String, RoundResult> player : members.judgeGameResults().entrySet()) {
            String playerName = player.getKey();
            int bettingAmount = members.getBettingAmount(playerName);
            result.put(
                    player.getKey(),
                    player.getValue()
                            .calculateProfit(bettingAmount)
            );
        }
        return result;
    }

    public List<String> getMemberNames() {
        return members.getMemberNames();
    }

    public String getDealerName() {
        return members.getDealerName();
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
}
