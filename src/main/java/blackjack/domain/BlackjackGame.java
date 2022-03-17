package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

import java.util.List;

public class BlackjackGame {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Participants participants;
    private final Deck deck;

    public BlackjackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public void firstCardDispensing() {
        distributeCard(getDealer());
        getPlayers().forEach(this::distributeCard);
    }

    public void distributeCard(Participant participant) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            participant.receiveCard(deck.distributeCard());
        }
    }

    public Player doPlayerGame(Player player) {
        player.receiveCard(deck.distributeCard());
        return player;
    }

    public Dealer doDealerGame() {
        final Dealer dealer = participants.getDealer();
        while (dealer.hasNextTurn()) {
            dealer.receiveCard(deck.distributeCard());
        }
        return dealer;
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }
}
