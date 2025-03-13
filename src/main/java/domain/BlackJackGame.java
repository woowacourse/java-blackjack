package domain;

import domain.card.GameCardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    private final GameCardDeck gameCardDeck;
    private final Participants participants;

    public BlackJackGame(final List<String> participantNames) {
        this.gameCardDeck = GameCardDeck.generateFullPlayingCard();
        this.participants = registerParticipants(participantNames);
    }

    private Participants registerParticipants(final List<String> participantNames) {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());
        for (String name : participantNames) {
            participants.add(new Player(name));
        }
        return new Participants(participants);
    }

    public void shuffleGameCards() {
        gameCardDeck.shuffle();
    }

    public GameCardDeck getGameCardDeck() {
        return gameCardDeck;
    }

    public Participants getParticipants() {
        return participants;
    }
}
