package blackjack.view.dto;

import blackjack.model.player.Player;

public record ExecutingPlayer(String name, String cards) {
    public static ExecutingPlayer from(final Player player) {
        return new ExecutingPlayer(player.getName().toString(), player.getCards().toString());
    }
}
