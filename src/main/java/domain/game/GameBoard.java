package domain.game;

import domain.card.GameCardDeck;
import domain.participant.Participant;
import domain.participant.Participants;

public class GameBoard {

    private final Participants participants;
    private final GameCardDeck gameCardDeck;

    public GameBoard(final Participants participants) {
        this.gameCardDeck = GameCardDeck.generateFullPlayingCard();
        this.participants = participants;
    }

    public void drawTwoCards() {
        participants.drawTwoCards(gameCardDeck);
    }

    public void drawCardTo(Participant participant) {
        participants.drawCardTo(gameCardDeck, participant);
    }

    public boolean ableToDraw(Participant participant) {
        return participants.ableToDraw(participant);
    }

    public void shufflePlayingCard() {
        gameCardDeck.shuffle();
    }

    public GameCardDeck getPlayingCard() {
        return gameCardDeck;
    }

    public Participants getParticipants() {
        return participants;
    }
}
