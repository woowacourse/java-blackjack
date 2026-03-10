package domain;

import constant.Word;
import domain.dto.GameResult;
import domain.dto.MemberStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameTable {

    private static final int BLACKJACK = 21;
    private static final int DEALER_DRAW_CONDITION = 16;

    private final Members members;
    private final Deck deck;

    public GameTable(List<String> playerNames, Deck deck) {
        this.members = new Members(playerNames);
        this.deck = deck;
    }

    public void distributeInitCard() {
        for (String memberName : members.getAllPlayerName()) {
            members.provideCard(memberName, deck.draw());
            members.provideCard(memberName, deck.draw());
        }
    }

    public boolean checkBust(String memberName) {
        return members.checkValue(memberName) > BLACKJACK;
    }

    public List<Card> drawForMember(String memberName) {
        members.provideCard(memberName, deck.draw());
        return members.findCardByName(memberName);
    }

    public boolean drawForDealer() {
        if (members.checkValue(Word.DEALER.getWord()) <= DEALER_DRAW_CONDITION) {
            members.provideCard(Word.DEALER.getWord(), deck.draw());
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
        gameResults.add(new GameResult(Word.DEALER.getWord(),
                members.judgeDealerGameResult()));

        Map<String, MatchResult> playerResults = members.judgePlayerGameResult();

        for (String playerName : playerResults.keySet()) {
            gameResults.add(new GameResult(playerName,
                    List.of(playerResults.get(playerName))));
        }
        return gameResults;
    }
}
