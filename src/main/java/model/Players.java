package model;

import controller.Continuation;
import java.util.ArrayList;
import java.util.List;
import view.InputView;

public class Players {
    private final List<Player> players;

    public Players(List<Player> values) {
        this.players = new ArrayList<>(values);
    }

    public static Players from(List<String> names) {
        return new Players(names.stream()
                .map(Player::new)
                .toList());
    }

    public void bet() {
        for (Player player : players) {
            player.betMoney(InputView.readPlayerBettingMoney(player.name()));
        }
    }

    public void receiveStartingCards(CardDispenser cardDispenser) {
        for (Player player : players) {
            cardDispenser.dispenseStartingCards(player);
        }
    }

    public void play(CardDispenser cardDispenser) {
        for (Player player : players) {
            drawCardIfAllowed(player, cardDispenser);
        }
    }

    private void drawCardIfAllowed(Player player, CardDispenser cardDispenser) {
        while (player.canHit()) {
            String inputCommand = InputView.readMoreCard(player.name());
            if (!Continuation.from(inputCommand).isContinue()) {
                break;
            }
            cardDispenser.dispenseOneCard(player);
        }
    }

    public List<Player> players() {
        return players;
    }
}