package blackJack.domain;

import blackJack.domain.card.Deck;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participant;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;

import java.util.List;

public class BlackJackGame {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Participants participants;
    private final Deck deck;

    public BlackJackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public void firstCardDispensing() {
        distributeCard(getDealer());
        getPlayers().forEach(this::distributeCard);
    }

    public void distributeCard(Participant participant) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            participant.receiveCard(deck.getCard());
        }
    }

    public Dealer doDealerGame() {
        final Dealer dealer = participants.getDealer();
        while (dealer.hasNextTurn()) {
            dealer.receiveCard(deck.getCard());
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
