package repository;

import domain.DrawStrategy;
import domain.GameTable;
import domain.vo.NameAndCardInfos;
import java.util.List;

public class GameTableRepository {

    private GameTable gameTable;

    public void save(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    public void addPlayer(String name, DrawStrategy drawStrategy) {
        gameTable.addPlayer(name, drawStrategy);
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
//        gameTable.recordCurrentGameResult();
    }
}
