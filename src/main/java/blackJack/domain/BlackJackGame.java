package blackJack.domain;

import java.util.List;

import blackJack.domain.card.Deck;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participant;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;

public class BlackJackGame {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(Participants participants) {
        this.deck = new Deck();
        this.participants = participants;
    }

    public void firstCardDispensing() {
        distributeCard(participants.getDealer(), INITIAL_CARD_COUNT);
        participants.getPlayers().forEach(player -> distributeCard(player, INITIAL_CARD_COUNT));
    }

    public void distributeCard(Participant participant, int count) {
        for (int i = 0; i < count; i++) {
            participant.receiveCard(deck.getCard());
        }
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }
}
