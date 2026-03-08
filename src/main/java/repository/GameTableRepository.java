package repository;

import domain.GameTable;
import domain.Participant;

public class GameTableRepository {

    private GameTable gameTable;

    public void save(GameTable gameTable) {
        this.gameTable = gameTable;
    }

    public void addPlayer(String name) {
        gameTable.addPlayer(name);
    }

    public void getDealerCards() {
    }
}
