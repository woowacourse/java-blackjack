package domain;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;

public class CardDistributor {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Deck deck;

    public CardDistributor() {
        this.deck = new Deck();
    }

    public void dealInitialCardsToParticipants(Dealer dealer, Players players) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            dealCardTo(dealer);
            for (Player player : players.getPlayers()) {
                dealCardTo(player);
            }
        }
    }

    public void dealCardTo(Participant participant) {
        participant.receive(deck.drawCard());
    }
}
