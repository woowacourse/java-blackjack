package controller;

import domain.Cards;
import domain.Dealer;
import domain.Deck;
import domain.Players;

import java.util.ArrayList;

import static view.InputView.printErrorMessage;
import static view.InputView.readPlayerNames;

public class EntityCreator {
    private final Players players;
    private final Dealer dealer;

    public EntityCreator() {
        new Deck();
        this.players = getValidPlayerNames();
        this.dealer = new Dealer(new Cards(new ArrayList<>()));
    }

    private Players getValidPlayerNames() {
        Players players;

        try {
            players = new Players(readPlayerNames());
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            players = getValidPlayerNames();
        }
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
