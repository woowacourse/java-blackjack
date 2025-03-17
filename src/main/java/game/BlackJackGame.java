package game;

import card.GameCardDeck;
import participant.Dealer;
import participant.Participant;
import participant.Participants;
import participant.Player;
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

    private void shuffleGameCards() {
        gameCardDeck.shuffle();
    }

    public void drawOneCards(Participant participant) {
        participant.drawCard(gameCardDeck, 1);
    }

    public void drawTwoCards() {
        shuffleGameCards();
        participants.drawTwoCards(gameCardDeck);
    }

    public int drawDealer() {
        Participant dealer = participants.findDealer();
        int count = 0;
        while (dealer.ableToDraw()) {
            count += 1;
            dealer.drawCard(gameCardDeck, 1);
        }
        return count;
    }

    public Participants getParticipants() {
        return participants;
    }

    public List<Participant> getOnlyPlayers() {
        return participants.findPlayers();
    }
}
