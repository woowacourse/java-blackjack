package domain;

import static constant.Word.*;

import domain.card.Card;
import domain.dto.MemberStatus;
import domain.member.Members;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameTable {

    private static final int BLACKJACK = 21;
    private static final int DEALER_DRAW_CONDITION = 16;

    private final Members members;

    public GameTable() {
        this.members = new Members();
    }

    public void joinPlayer(List<String> playerNames) {
        members.appendPlayer(playerNames);
    }

    public boolean checkBust(String memberName) {
        return members.checkValue(memberName) > BLACKJACK;
    }

    public List<Card> draw(String memberName, Card card) {
        if (members.isDealer(memberName)) {
            return drawDealer(card);
        }
        return drawPlayer(memberName, card);
    }

    public boolean draw(Card card) {
        if (members.checkDealerValue() <= DEALER_DRAW_CONDITION) {
            members.provideCardToDealer(card);
            return true;
        }
        return false;
    }

    public List<MemberStatus> checkPlayerStatuses() {
        return members.getAllPlayerName()
                .stream()
                .map(name -> {
                    List<Card> cards = members.findCardByName(name);
                    int totalValue = members.checkValue(name);
                    return new MemberStatus(name, cards, totalValue);
                }).collect(Collectors.toList());
    }

    public MemberStatus checkDealerStatus() {
        List<Card> cards = members.dealerCards();
        int totalValue = members.checkDealerValue();
        return new MemberStatus(DEALER.format(), cards, totalValue);
    }

    public Map<String, RoundResult> checkGameResult() {
        return members.judgeGameResults();
    }

    private List<Card> drawPlayer(String memberName, Card card) {
        members.provideCardToPlayer(memberName, card);
        return members.findCardByName(memberName);
    }

    private List<Card> drawDealer(Card card) {
        members.provideCardToDealer(card);
        return members.dealerFirstCard();
    }
}
