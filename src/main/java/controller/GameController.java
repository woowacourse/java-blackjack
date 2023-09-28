package controller;

import model.Deck;
import model.Players;
import view.GameView;

import java.io.IOException;

public class GameController {
    
    private final Deck deck;
    private final GameView view;
    private final Players players;

    public GameController(final Deck deck, final GameView view) throws IOException {
        this.deck = deck;
        this.view = view;
        this.players = joinPlayers(view.getPlayerNameInput());
    }

    public void play() {
        
    }

    private Players joinPlayers(String nameInput) {
        Players players = Players.createDefault();
        players.joinPlayer(nameInput);

        return players;
    }
}
