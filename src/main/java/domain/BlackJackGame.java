package domain;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;
    private final Decks decks;

    public BlackJackGame(final Decks decks) {
        this.decks = decks;
    }

    public void prepareCards(Gamers gamers) {
        for (Gamer gamer : gamers.getGamers()) {
            drawTwoCardsForGamer(gamer);
        }
    }

    private void drawTwoCardsForGamer(final Gamer gamer) {
        for (int count = 0; count < INITIAL_CARD_COUNT; count++) {
            gamer.hit(decks.draw());
        }
    }

    public boolean succeededGiving(Gamer gamer) {
        if(!gamer.isStay()) {
            gamer.hit(decks.draw());
            return true;
        }
        return false;
    }

//    public List<Card> getGamerHandAt(int index) {
//        return gamers.getHandAt(index);
//    }
}
