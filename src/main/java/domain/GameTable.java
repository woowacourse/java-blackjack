package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.member.Money;
import dto.GameResult;
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
        return members.isPlayerFinished(memberName);
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
        // 컴파일 에러를 막기 위해 기존 구조만 유지
        return new ArrayList<>();
    }
}
