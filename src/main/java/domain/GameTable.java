package domain;

import constant.Word;
import domain.card.Card;
import domain.card.Deck;
import dto.GameResult;
import dto.MemberStatus;
import domain.member.Members;
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
        for (String memberName : members.getAllMemberName()) {
            members.provideCardToMember(memberName, deck.draw());
            members.provideCardToMember(memberName, deck.draw());
        }
    }

    public boolean checkBust(String memberName) {
        return members.checkValue(memberName) > BLACKJACK;
    }

    public List<Card> drawForMember(String memberName) {
        members.provideCardToMember(memberName, deck.draw());
        return members.findCardByName(memberName);
    }

    public boolean drawForDealer() {
        if (members.checkValue(Word.DEALER.getWord()) <= DEALER_DRAW_CONDITION) {
            members.provideCardToMember(Word.DEALER.getWord(), deck.draw());
            return true;
        }
        return false;
    }

    public List<MemberStatus> checkMemberStatuses() {
        return members.getAllMemberName()
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
                members.determineDealerGameResult()));

        List<GameResult> playerResults =members.getAllMemberName().stream()
                .filter(name -> !name.equals(Word.DEALER.getWord()))
                .map(name -> new GameResult(name, List.of(members.determinePlayerGameResult(name))))
                .toList();
        gameResults.addAll(playerResults);
        return gameResults;
    }
}
