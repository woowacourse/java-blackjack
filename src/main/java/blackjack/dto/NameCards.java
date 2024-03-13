package blackjack.dto;

import blackjack.model.deck.Card;
import blackjack.model.participant.Player;
import java.util.List;

public record NameCards(String name, List<Card> cards) {

    public static NameCards from(Player player) {
        return new NameCards(player.getName(), player.openCards());
    }
}
