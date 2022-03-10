package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.player.Player;
import java.util.List;

public class PlayerCards {

    private final String name;
    private final List<Card> cards;

    private PlayerCards(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerCards toPlayerFirstCards(final Player player) {
        return new PlayerCards(
                player.getName(),
                player.firstDrawCard()
        );
    }

    public static PlayerCards toPlayerCards(final Player player) {
        return new PlayerCards(
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
