package domain;

import java.util.List;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;
    private final Gamers gamers;
    private final Decks decks;

    public BlackJackGame(final Gamers gamers, final Decks decks) {
        this.gamers = gamers;
        this.decks = decks;
    }

    public void prepareCards() {
        for (Gamer gamer : gamers.getGamers()) {
            drawTwoCardsForGamer(gamer);
        }
    }

    private void drawTwoCardsForGamer(final Gamer gamer) {
        for (int count = 0; count < INITIAL_CARD_COUNT; count++) {
            gamer.hit(decks.draw());
        }
    }

    public List<Card> getGamerHandAt(int index) {
        return gamers.getHandAt(index);
    }
}
