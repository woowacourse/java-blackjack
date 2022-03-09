package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.player.Player;
import java.util.List;

public class PlayerInfo {

    private final String name;
    private final List<Card> cards;

    private PlayerInfo(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerInfo toPlayerInitInfo(final Player player) {
        return new PlayerInfo(
                player.getName(),
                player.initCards()
        );
    }

    public static PlayerInfo toPlayerInfo(final Player player) {
        return new PlayerInfo(
                player.getName(),
                player.cards()
        );
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
