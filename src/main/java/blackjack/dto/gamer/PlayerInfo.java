package blackjack.dto.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Player;

import java.util.List;

public record PlayerInfo(String name, List<Card> cards) {

    public static PlayerInfo from(final Player player) {
        return new PlayerInfo(player.getName(), player.cardStatus());
    }
}
