package domain;

import java.util.List;

public class BlackjackGame {

    private Participants participants;
    private Deck deck;

    public void initialize(List<String> playerNames) {
        participants = Participants.create(playerNames);
        deck = Deck.create();

        RandomShuffleStrategy shuffleStrategy = new RandomShuffleStrategy();
        deck.shuffle(shuffleStrategy);
    }

    public boolean hasDrawablePlayer() {
        return participants.hasDrawablePlayer();
    }

    public String nextDrawablePlayer() {
        return participants.nextDrawablePlayerName();
    }

    public void handOutCardToNextPlayer() {
        participants.handOutCard(deck.draw());
    }
}
