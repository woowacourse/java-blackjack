package model.blackjackgame;

import java.util.List;
import model.participants.player.Player;

public class Bettings {

    private final List<Betting> bettings;

    public Bettings(List<Betting> bettings) {
        this.bettings = bettings;
    }

    public Betting findBettingByPlayer(Player player) {
        return bettings.stream()
                .filter(betting -> betting.getPlayer().getName().equals(player.getName()))
                .findFirst()
                .orElseThrow();
    }
}
