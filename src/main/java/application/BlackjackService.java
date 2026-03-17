package application;

import domain.card.Card;
import domain.card.Deck;
import domain.member.Members;
import domain.member.Money;
import dto.RoundResult;
import dto.GameResult;
import dto.MemberStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackService {

    private final Members members;
    private final Deck deck;

    public BlackjackService(Map<String, Integer> playerBetAmounts, Deck deck) {
        Map<String, Money> playerBets = new HashMap<>();
        for (String name : playerBetAmounts.keySet()) {
            Money betMoney = new Money(playerBetAmounts.get(name));
            playerBets.put(name, betMoney);
        }
        this.members = new Members(playerBets);
        this.deck = deck;
        distributeInitCard();
    }

    private void distributeInitCard() {
        members.provideCardToDealer(deck.draw());
        members.provideCardToDealer(deck.draw());
        for (String playerName : members.getAllPlayerName()) {
            members.provideCardToPlayer(playerName, deck.draw());
            members.provideCardToPlayer(playerName, deck.draw());
        }
    }

    public boolean isFinishedByName(String playerName) {
        return members.isPlayerFinishedByName(playerName);
    }

    public RoundResult startOneRound(String memberName) {
        List<Card> playerCards = drawForMember(memberName);

        boolean isBust = isPlayerBust(memberName);

        return new RoundResult(playerCards, isBust);
    }

    private List<Card> drawForMember(String playerName) {
        members.provideCardToPlayer(playerName, deck.draw());
        return members.findCardByName(playerName);
    }

    private boolean isPlayerBust(String playerName) {
        return members.isPlayerBust(playerName);
    }

    public void endPlayerRound(String playerName) {
        members.changePlayerStateToStay(playerName);
    }

    public boolean checkDealerDrawable() {
        if (members.canTheDealerDraw()) {
            members.provideCardToDealer(deck.draw());
            members.changeDealerStateToStay();
            return true;
        }
        members.changeDealerStateToStay();
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

    public List<GameResult> getGameResults() {
        Map<String, Integer> profits = members.calculateFinalProfits();
        return profits.entrySet().stream()
                .map(entry -> new GameResult(entry.getKey(), entry.getValue()))
                .toList();
    }
}
