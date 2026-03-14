package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.member.Money;
import dto.MemberStatus;
import domain.member.Members;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameTable {

    private final Members members;
    private final Deck deck;

    public GameTable(Map<String, Money> playerBets, Deck deck) {
        this.members = new Members(playerBets);
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

    public boolean isPlayerBust(String memberName) {
        return members.isPlayerBust(memberName);
    }

    public void changePlayerState(String playerName) {
        members.changePlayerStateToStay(playerName);
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

    public List<MemberStatus> getMemberStatuses() {
        List<MemberStatus> memberStatuses = new ArrayList<>();
        memberStatuses.add(
                new MemberStatus(members.getDealerName(), members.findDealerCards(), members.checkDealerScore()));

        members.getAllPlayerName().stream()
                .map(name -> new MemberStatus(name, members.findCardByName(name), members.checkPlayerScore(name)))
                .forEach(memberStatuses::add);
        return List.copyOf(memberStatuses);
    }

    public Map<String, Integer> getFinalProfits() {
        return members.calculateFinalProfits();
    }
}
