package util;

import domain.card.Card;
import domain.game.deck.Deck;
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
        Player player = new Player(name, 1);
        player.pickCard(new Deck(cards), cards.size());
        return player;
    }
}
