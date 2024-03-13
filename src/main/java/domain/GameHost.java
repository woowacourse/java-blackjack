package domain;

import java.util.List;
import java.util.Random;

public class GameHost {
    private static final int DECK_SIZE = Deck.getSize();
    private static final Random random = new Random();

    private final Dealer dealer = new Dealer();
    private final Gamers gamers;

    public GameHost(final List<String> gamerNames) {
        this.gamers = new Gamers(gamerNames.stream()
                .map(Gamer::new)
                .toList());
    }

    public void readyGame() {
        dealer.drawTwoCard(getRandomCardIndex(), getRandomCardIndex());
        for (Gamer gamer : gamers.listOf()) {
            gamer.drawTwoCard(getRandomCardIndex(), getRandomCardIndex());
        }
    }

    private int getRandomCardIndex() {
        return random.nextInt(DECK_SIZE);
    }

    public Dealer findPlayingDealer() {
        return dealer;
    }

    public Gamers findPlayingGamers() {
        return gamers;
    }

    public int cardDrawCountOfDealer(final Dealer dealer) {
        int count = 0;
        while (dealer.isNotUpToThreshold()) {
            dealer.drawOneCard(random.nextInt(DECK_SIZE));
            count++;
        }
        return count;
    }

    public void drawOneCardToGamer(final Gamer gamer) {
        gamer.drawOneCard(getRandomCardIndex());
    }
}
