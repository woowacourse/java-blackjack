package controller;

import model.Deck;
import view.GameView;

public class GameController {

    private final Deck deck;
    private final GameView view;

    public GameController(final Deck deck, final GameView view) {
        this.deck = deck;
        this.view = view;
    }
}
