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
        for (String playerName : members.getAllPlayerName()) {
            members.provideCardToPlayer(playerName, deck.draw());
            members.provideCardToPlayer(playerName, deck.draw());
        }
    }

    public boolean isPlayerBust(String playerName) {
        return members.isPlayerBust(playerName);
    }

    public boolean isPlayerFinished(String playerName) {
        return members.isPlayerFinishedByName(playerName);
    }

    public void changePlayerState(String playerName) {
        members.changePlayerStateToStay(playerName);
    }

    public List<Card> drawForMember(String playerName) {
        members.provideCardToPlayer(playerName, deck.draw());
        return members.findCardByName(playerName);
    }

    public boolean drawForDealer() {
        if (members.canTheDealerDraw()) {
            members.provideCardToDealer(deck.draw());
            return true;
        }
        return false;
    }

    public List<MemberStatus> getMemberStatuses() {
        List<MemberStatus> memberStatuses = new ArrayList<>();
        memberStatuses.add(
                new MemberStatus(members.getDealerName(), members.findDealerCards(), members.getDealerScore()));

        members.getAllPlayerName().stream()
                .map(name -> new MemberStatus(name, members.findCardByName(name), members.getPlayerScore(name)))
                .forEach(memberStatuses::add);
        return List.copyOf(memberStatuses);
    }

    public Map<String, Integer> getFinalProfits() {
        return members.calculateFinalProfits();
    }
}
