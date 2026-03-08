package repository;

import domain.GameTable;

public class GameTableRepository {

    private GameTable gameTable;

    public void save(GameTable gameTable) {
        this.gameTable = gameTable;
    }
}
