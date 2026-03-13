package domain.table;

import domain.member.Member;
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
        Member member = members.findByName(memberName);
        members.draw(member, card);
    }

    public boolean canDealerDraw() {
        Member dealer = members.findDealer();
        return members.getValue(dealer) <= DEALER_DRAW_CONDITION;
    }

    public boolean checkBust(String memberName) {
        Member member = members.findByName(memberName);
        return members.getValue(member) > BLACKJACK;
    }

    public boolean checkBlackjack(String playerName) {
        Member player = members.findByName(playerName);
        return members.getValue(player) == BLACKJACK;
    }

    public void applyBlackjackBonus(String playerName) {
        Member player = members.findByName(playerName);
        members.applyBlackjackBonus(player);
    }

    public Map<String, Integer> checkGameResult() {
        LinkedHashMap<String, Integer> results = getPlayerResult();
        int dealerAmount = results.values().stream()
                .mapToInt(result -> -1 * result)
                .sum();
        results.putFirst(members.getDealerName(), dealerAmount);
        return results;
    }

    public boolean checkDealer(String memberName) {
        Member member = members.findByName(memberName);
        return members.isDealer(member);
    }

    public List<String> getMemberNames() {
        return members.getMemberNames();
    }

    public String getDealerName() {
        return members.getDealerName();
    }

    public List<Card> getCards(String memberName) {
        Member member = members.findByName(memberName);
        return members.findCard(member);
    }

    public List<Card> getFirstCards(String memberName) {
        Member member = members.findByName(memberName);
        return members.getFirstCards(member);
    }

    public int memberPoint(String playerName) {
        Member player = members.findByName(playerName);
        return members.getValue(player);
    }

    private LinkedHashMap<String, Integer> getPlayerResult() {
        LinkedHashMap<String, Integer> results = new LinkedHashMap<>();
        for (Entry<String, RoundResult> player : members.judgeGameResults().entrySet()) {
            String playerName = player.getKey();
            int bettingAmount = members.getBettingAmount(playerName);
            results.put(
                    player.getKey(),
                    player.getValue()
                            .calculateProfit(bettingAmount)
            );
        }
        return results;
    }
}
