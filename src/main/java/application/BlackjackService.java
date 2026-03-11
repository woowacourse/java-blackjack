package application;

import domain.card.Card;
import domain.GameTable;
import dto.RoundResult;
import domain.card.StandardDeck;
import dto.GameResult;
import dto.MemberStatus;
import java.util.List;

public class BlackjackService {

    private GameTable gameTable;

    public BlackjackService() {
    }

    public void initializeGame(List<String> playerNames) {
        this.gameTable = new GameTable(playerNames, new StandardDeck());
        gameTable.distributeInitCard();
    }

    public RoundResult startOneRound(String memberName) {
        List<Card> playerCards = gameTable.drawForMember(memberName);

        boolean isBust = gameTable.checkBust(memberName);

        return new RoundResult(playerCards, isBust);
    }

    public boolean checkDealerDrawable() {
        return gameTable.drawForDealer();
    }

    public List<MemberStatus> getMemberStatuses() {
        return gameTable.checkMemberStatuses();
    }

    public List<GameResult> getGameResults() {
        return gameTable.checkGameResult();
    }
}
