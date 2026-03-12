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
        List<Card> playerCards = getGameTable().drawForMember(memberName);

        boolean isBust = getGameTable().checkBust(memberName);

        return new RoundResult(playerCards, isBust);
    }

    public boolean checkDealerDrawable() {
        return getGameTable().drawForDealer();
    }

    public List<MemberStatus> getMemberStatuses() {
        return getGameTable().checkMemberStatuses();
    }

    public List<GameResult> getGameResults() {
        return getGameTable().checkGameResult();
    }

    private GameTable getGameTable() {
        if (gameTable == null) {
            throw new IllegalStateException("게임이 초기화가 되지 않았습니다.");
        }
        return gameTable;
    }
}
