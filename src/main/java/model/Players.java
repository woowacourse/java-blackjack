package model;

import controller.Continuation;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

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
        boolean drawCard = false;
        while (canHitMore(player)) {
            drawCard = true;
            cardDispenser.dispenseOneCard(player);
            printCardByPlayer(player);
        }
        if (!drawCard) {
            printCardByPlayer(player);
        }
    }

    private boolean canHitMore(Player player) {
        return player.canHit() && readContinuation(player).isContinue();
    }

    private Continuation readContinuation(Player player) {
        String inputCommand = InputView.readMoreCard(player.name());
        return Continuation.from(inputCommand);
    }

    private void printCardByPlayer(Player player) {
        OutputView.printCardByPlayer(player);
    }

    public List<Player> players() {
        return players;
    }
}