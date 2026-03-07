package domain;

import static constant.Word.*;

import domain.dto.GameResult;
import domain.dto.MemberStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameTable {

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
        if (members.checkValue(DEALER.format()) <= DEALER_DRAW_CONDITION) {
            members.provideCard(DEALER.format(), card);
            return true;
        }
        return false;
    }

    public List<MemberStatus> checkMemberStatuses() {
        return members.getAllPlayerName()
                .stream()
                .map(name -> {
                    List<Card> cards = members.findCardByName(name);
                    int totalValue = members.checkValue(name);
                    return new MemberStatus(name, cards, totalValue);
                }).toList();
    }

    public List<GameResult> checkGameResult() {
        List<GameResult> gameResults = new ArrayList<>();
        gameResults.add(new GameResult(DEALER.format(),
                members.judgeDealerGameResult(DEALER.format())));

        Map<String, Boolean> playerResults = members.judgePlayerGameResult(DEALER.format());

        for (String playerName : playerResults.keySet()) {
            gameResults.add(new GameResult(playerName,
                    List.of(playerResults.get(playerName))));
        }
        return gameResults;
    }
}
