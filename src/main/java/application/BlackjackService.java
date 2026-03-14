package application;

import domain.card.Card;
import domain.GameTable;
import domain.member.Money;
import dto.RoundResult;
import domain.card.StandardDeck;
import dto.GameResult;
import dto.MemberStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackService {

    private GameTable gameTable;

    public BlackjackService() {
    }

    public void initializeGame(Map<String, Integer> playerBetAmounts) {
        Map<String, Money> playerBets = new HashMap<>();
        for (String name : playerBetAmounts.keySet()) {
            Money betMoney = new Money(playerBetAmounts.get(name));
            playerBets.put(name, betMoney);
        }
        this.gameTable = new GameTable(playerBets, new StandardDeck());
        gameTable.distributeInitCard();
    }

    public RoundResult startOneRound(String memberName) {
        List<Card> playerCards = getGameTable().drawForMember(memberName);

        boolean isBust = getGameTable().isPlayerBust(memberName);

        return new RoundResult(playerCards, isBust);
    }

    public void endPlayerRound(String playerName) {
        getGameTable().changePlayerState(playerName);
    }

    public boolean checkDealerDrawable() {
        return getGameTable().drawForDealer();
    }

    public List<MemberStatus> getMemberStatuses() {
        return getGameTable().getMemberStatuses();
    }

    public List<GameResult> getGameResults() {
        Map<String, Integer> profits = gameTable.getFinalProfits();
        return profits.entrySet().stream()
                .map(entry -> new GameResult(entry.getKey(), entry.getValue()))
                .toList();
    }

    private GameTable getGameTable() {
        if (gameTable == null) {
            throw new IllegalStateException("게임이 초기화가 되지 않았습니다.");
        }
        return gameTable;
    }
}
