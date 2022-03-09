package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.List;

public class PlayerInfo {

    private final String name;
    private final List<Card> cards;

    private PlayerInfo(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerInfo dealerToInitInfo(final Dealer dealer) {
        return new PlayerInfo(
                dealer.getName(),
                dealer.initCards()
        );
    }

    public static PlayerInfo playerToInfo(final Player player) {
        return new PlayerInfo(
                player.getName(),
                player.getCards()
        );
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
