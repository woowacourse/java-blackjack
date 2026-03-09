package repository;

import domain.GameTable;
import domain.PlayedGameResult;
import domain.vo.NameAndCardInfos;
import java.util.List;

public class GameTableRepository {

    private GameTable gameTable;

    public void save(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    public NameAndCardInfos getDealerCards() {
        return gameTable.dealerCardsInfo();
    }

    public List<NameAndCardInfos> getAllPlayersCards() {
        return gameTable.allPlayerCardInfos();
    }

    public List<String> getAllPlayerNames() {
        return gameTable.allPlayerNames();
    }

    public void distributeInitialCards() {
        gameTable.allParticipantsDrawInitialCards();
    }

    public NameAndCardInfos getCurrentPlayerCards() {
        return gameTable.currentPlayerCardInfos();
    }

    public String getCurrentPlayerName() {
        return gameTable.currentPlayerName();
    }

    public void currentPlayerDrawCard() {
        gameTable.currentPlayerDrawCard();
    }

    public boolean isCurrentPlayerPlayable() {
        return gameTable.isCurrentPlayerPlayable();
    }

    public boolean hasWaitingPlayers() {
        return gameTable.hasWaitingPlayers();
    }

    public void recordCurrentGameResult() {
        gameTable.recordCurrentGameResult();
    }

    public boolean isDealerPlayable() {
        return gameTable.isDealerPlayable();
    }

    public void dealerDrawCard() {
        gameTable.dealerDrawCard();
    }

    public void recordDealerGameResult() {
        gameTable.recordDealerGameResult();
    }

    public PlayedGameResult dealerResult() {
        return gameTable.dealerResult();
    }

    public List<PlayedGameResult> playerResults() {
        return gameTable.playerResults();
    }
}
