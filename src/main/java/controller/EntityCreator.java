package controller;

import domain.Cards;
import domain.Dealer;
import domain.Players;

import java.util.ArrayList;

import static view.InputView.printErrorMessage;
import static view.InputView.readPlayerNames;

public class EntityCreator {
    private final Players players;
    private final Dealer dealer;

    public EntityCreator() {
        this.players = getValidPlayerNames();
        this.dealer = new Dealer(new Cards(new ArrayList<>()));
    }

    private Players getValidPlayerNames() {
        try {
            return new Players(readPlayerNames());
        } catch (RuntimeException exception) {
            printErrorMessage(exception);
            return getValidPlayerNames();
        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }
}
