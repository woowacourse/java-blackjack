package blackjack.dto.gamer;

import blackjack.domain.gamer.Player;

public record PlayerState(String name, GamerCardState cardState) {

    public static PlayerState from(final Player player) {
        return new PlayerState(player.getName(), player.cardStatus());
    }
}
