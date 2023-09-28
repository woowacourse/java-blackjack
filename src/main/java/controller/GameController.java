package controller;

import model.Deck;
import model.Players;
import view.GameView;

import java.io.IOException;

public class GameController {

    private static final int INIT_CARD = 2;

    private final Deck deck;
    private final GameView view;
    private final Players players;

    public GameController(final Deck deck, final GameView view) throws IOException {
        this.deck = deck;
        this.view = view;
        this.players = joinPlayers(view.getPlayerNameInput());
    }

    public void play() {
        view.giveInitCardInfo(players.getDealer(), players.getPlayerNameList(), INIT_CARD);
    }

    private Players joinPlayers(String nameInput) {
        Players players = Players.createDefault();
        players.joinPlayer(nameInput);

        return players;
    }
}
