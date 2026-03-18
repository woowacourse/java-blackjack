package view.dto;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;

public record ParticipantCards(
        String name,
        List<Card> cards
) {

    public ParticipantCards(String name, List<Card> cards) {
        this.name = name;
        this.cards = List.copyOf(cards);
    }

    public static ParticipantCards fromPlayer(Player player) {
        return new ParticipantCards(
                player.name(),
                player.cardsInHand());
    }

    public static ParticipantCards fromDealerUpCard(Dealer dealer) {
        return new ParticipantCards(
                dealer.name(),
                List.of(dealer.upCard())
        );
    }

}
