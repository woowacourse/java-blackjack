package domain.game;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;
import strategy.CardGenerator;

public class BlackjackGame {

    private static final int STARTING_CARDS_AMOUNT = 2;

    private final Participants participants;
    private final Deck deck;

    public BlackjackGame(Participants participants, CardGenerator cardGenerator) {
        this.participants = participants;
        this.deck = Deck.from(cardGenerator);
    }

    public void distributeStartingCards() {
        for (Participant participant : getPlayers()) {
            participant.receive(deck.drawCards(STARTING_CARDS_AMOUNT));
        }
        getDealer().receive(deck.drawCards(STARTING_CARDS_AMOUNT));
    }

    public void giveOneCard(Participant participant) {
        if (participants.doesNotContain(participant)) {
            throw new IllegalArgumentException("[ERROR] 해당 게임의 참여자가 아닙니다.");
        }
        if (participant.isReceivable()) {
            participant.receive(deck.drawCard());
        }
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }
}
