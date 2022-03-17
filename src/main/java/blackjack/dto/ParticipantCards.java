package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.paticipant.Dealer;
import blackjack.domain.paticipant.Player;
import java.util.List;

public class ParticipantCards {

    private final String name;
    private final List<Card> cards;

    private ParticipantCards(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static ParticipantCards createDealerFirstCards(final Dealer dealer) {
        return new ParticipantCards(dealer.getName(), List.of(dealer.firstCard()));
    }

    public static ParticipantCards from(final Player player) {
        return new ParticipantCards(player.getName(), player.cards());
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
