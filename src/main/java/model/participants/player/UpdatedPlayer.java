package model.participants.player;

import java.util.Objects;
import model.blackjackgame.BlackjackGame;

public record UpdatedPlayer(Player player) {
    public UpdatedPlayer(BlackjackGame blackjackGame, Player player) {
        this(blackjackGame.getPlayers().getPlayers().stream().filter(updatedPlayer -> Objects.equals(
                updatedPlayer.getName(), player.getName())).findFirst().orElse(player));
    }
}
