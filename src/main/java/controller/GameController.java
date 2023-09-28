package controller;

import model.deck.Deck;
import model.name.Name;
import model.players.Players;
import view.GameView;

import java.io.IOException;

public class GameController {

    private static final int GOAL_VALUE = 21;
    private static final int INIT_GIVE_CARDS = 2;

    private final Deck deck;
    private final GameView view;
    private final Players players;

    public GameController(final Deck deck, final GameView view) throws IOException {
        this.deck = deck;
        this.view = view;
        this.players = joinPlayers(view.getPlayerNameInput());
    }

    public void play() throws IOException {
        view.giveInitCardAlert(Name.getDealer(), players.getPlayerNamesExceptDealer(), INIT_GIVE_CARDS);
        players.giveInitialCards(deck, INIT_GIVE_CARDS);

        view.print(players.showInitialDealerStatus());
        for (String status : players.showInitialStatusExceptDealer()) {
            view.print(status);
        }

        System.out.println();

        for (String name : players.getPlayerNameValuesExceptDealer()) {
            while (view.isWantMoreCard(name) && players.isNotExceed(name, GOAL_VALUE)) {
                players.giveOneCard(deck, name);
                view.print(players.printShowStatus(name));
            }
        }
    }

    private Players joinPlayers(String nameInput) {
        Players players = Players.createDefault();
        players.joinPlayers(nameInput);

        return players;
    }
}
