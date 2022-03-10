package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;

public class PlayerDto {

    private final String name;
    private final List<Card> cards;

    private PlayerDto(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerDto dealerToInitInfo(final Dealer dealer) {
        return new PlayerDto(
                dealer.getName(),
                dealer.initCards()
        );
    }

    public static PlayerDto playerToInfo(final Player player) {
        return new PlayerDto(
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
