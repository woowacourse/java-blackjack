package domain;

import domain.card.Card;
import domain.member.Members;
import java.util.List;
import java.util.Map;

public class GameTable {

    private static final int BLACKJACK = 21;
    private static final int DEALER_DRAW_CONDITION = 16;

    private final Members members;

    public GameTable() {
        this.members = new Members();
    }

    public List<String> allPlayers() {
        return members.getAllPlayerNames();
    }

    public void joinPlayer(List<String> playerNames) {
        members.appendPlayer(playerNames);
    }

    public List<Card> drawByName(String memberName, Card card) {
        if (members.isDealer(memberName)) {
            drawDealer(card);
            return members.dealerFirstCard();
        }
        return drawPlayer(memberName, card);
    }

    public void drawDealer(Card card) {
        members.provideCardToDealer(card);
    }

    public boolean dealerDrawable() {
        return members.checkDealerValue() <= DEALER_DRAW_CONDITION;
    }

    public boolean checkBust(String memberName) {
        return members.checkValue(memberName) > BLACKJACK;
    }

    public List<Card> playerCards(String playerName) {
        return members.findCardByName(playerName);
    }

    public List<Card> dealerCards() {
        return members.dealerCards();
    }

    public int playerPoint(String playerName) {
        return members.checkValue(playerName);
    }

    public int dealerPoint() {
        return members.checkDealerValue();
    }

    public Map<String, RoundResult> checkGameResult() {
        return members.judgeGameResults();
    }

    private List<Card> drawPlayer(String memberName, Card card) {
        members.provideCardToPlayer(memberName, card);
        return members.findCardByName(memberName);
    }
}
