package repository;

import domain.GameTable;
import domain.vo.NameAndCardInfos;
import java.util.List;

public class GameTableRepository {

    private GameTable gameTable;

    public void save(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    public void addPlayer(String name) {
        gameTable.addPlayer(name);
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
}
