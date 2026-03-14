package domain;

import domain.card.Card;
import domain.card.Deck;
import dto.GameResult;
import dto.MemberStatus;
import domain.member.Members;
import java.util.ArrayList;
import java.util.List;

public class GameTable {

    private static final int BLACKJACK = 21;

    private final Members members;
    private final Deck deck;

    public GameTable(List<String> playerNames, Deck deck) {
        this.members = new Members(playerNames);
        this.deck = deck;
    }

    public void distributeInitCard() {
        members.provideCardToDealer(deck.draw());
        members.provideCardToDealer(deck.draw());
        for (String memberName : members.getAllPlayerName()) {
            members.provideCardToPlayer(memberName, deck.draw());
            members.provideCardToPlayer(memberName, deck.draw());
        }
    }

    public boolean checkBust(String memberName) {
        return members.checkPlayerScore(memberName) > BLACKJACK;
    }

    public List<Card> drawForMember(String memberName) {
        members.provideCardToPlayer(memberName, deck.draw());
        return members.findCardByName(memberName);
    }

    public boolean drawForDealer() {
        if (members.isMeetTheDrawConditionForDealer()) {
            members.provideCardToDealer(deck.draw());
            return true;
        }
        return false;
    }

    public List<MemberStatus> checkMemberStatuses() {
        List<MemberStatus> memberStatuses = new ArrayList<>();
        memberStatuses.add(
                new MemberStatus(members.getDealerName(), members.findDealerCards(), members.checkDealerScore()));

        members.getAllPlayerName().stream()
                .map(name -> new MemberStatus(name, members.findCardByName(name), members.checkPlayerScore(name)))
                .forEach(memberStatuses::add);
        return List.copyOf(memberStatuses);
    }

    public List<GameResult> checkGameResult() {
        List<GameResult> gameResults = new ArrayList<>();
        gameResults.add(new GameResult(members.getDealerName(),
                members.determineDealerGameResult()));

        List<GameResult> playerResults = members.getAllPlayerName().stream()
                .map(name -> new GameResult(name, List.of(members.determinePlayerGameResult(name))))
                .toList();
        gameResults.addAll(playerResults);
        return gameResults;
    }
}
