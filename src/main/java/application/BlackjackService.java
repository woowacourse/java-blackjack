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

    private final GameTable gameTable;

    public BlackjackService(Map<String, Integer> playerBetAmounts) {
        Map<String, Money> playerBets = new HashMap<>();
        for (String name : playerBetAmounts.keySet()) {
            Money betMoney = new Money(playerBetAmounts.get(name));
            playerBets.put(name, betMoney);
        }
        this.gameTable = new GameTable(playerBets, new StandardDeck());
        gameTable.distributeInitCard();
    }

    public boolean isFinishedByName(String playerName) {
        return gameTable.isPlayerFinished(playerName);
    }

    public RoundResult startOneRound(String memberName) {
        List<Card> playerCards = gameTable.drawForMember(memberName);

        boolean isBust = gameTable.isPlayerBust(memberName);

        return new RoundResult(playerCards, isBust);
    }

    public void endPlayerRound(String playerName) {
        gameTable.changePlayerState(playerName);
    }

    public boolean checkDealerDrawable() {
        return gameTable.drawForDealer();
    }

    public List<MemberStatus> getMemberStatuses() {
        return gameTable.getMemberStatuses();
    }

    public List<GameResult> getGameResults() {
        Map<String, Integer> profits = gameTable.getFinalProfits();
        return profits.entrySet().stream()
                .map(entry -> new GameResult(entry.getKey(), entry.getValue()))
                .toList();
    }
}
