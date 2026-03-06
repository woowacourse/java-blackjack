package controller;

import domain.GameTable;

public class Controller {

    private GameTable gameTable;

    public void run() {
        setupPhase();
        gamePhase();
    }
}
