package util;

import static domain.participant.Participants.CACHED_DEALER;

import controller.dto.request.PlayerBettingMoney;
import domain.card.Card;
import domain.game.deck.Deck;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;

public final class ParticipantSupplier {
    public static Dealer createDealer(final List<Card> cards) {
        CACHED_DEALER.pickCard(new Deck(cards), cards.size());
        return CACHED_DEALER;
    }

    public static Player createPlayer(final String name, final List<Card> cards) {
        Player player = new Player(name, 1);
        player.pickCard(new Deck(cards), cards.size());
        return player;
    }

    public static Participants createParticipants() {
        return Participants.from(
                List.of(new PlayerBettingMoney("pobi", 1))
        );
    }
}
