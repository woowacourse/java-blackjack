package util;

import domain.Deck;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;

public final class ParticipantSupplier {
    public static Dealer createDealer(final List<Card> cards) {
        Dealer dealer = new Dealer();
        dealer.pickCard(new Deck(cards), cards.size());
        return dealer;
    }

    public static Player createPlayer(final String name, final List<Card> cards) {
        Player player = new Player(name);
        player.pickCard(new Deck(cards), cards.size());
        return player;
    }
}
