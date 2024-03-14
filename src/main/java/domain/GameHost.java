package domain;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameHost {
    private static final int DECK_SIZE = Deck.getSize();
    private static final Random random = new Random();

    private final Dealer dealer;
    private final Gamers gamers;

    public GameHost(final List<String> gamerNames) {
        this.dealer = new Dealer();
        this.gamers = new Gamers(gamerNames.stream()
                .map(Gamer::new)
                .toList());
    }

    public GameHost(final List<String> gamerNames, final Dealer dealer) {
        this.dealer = dealer;
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

    public int cardDrawCountOfDealer() {
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

    public List<String> getGamerNames() {
        return Collections.unmodifiableList(gamers.getNames());
    }

    public Hand getDealerHand() {
        return dealer.getHand();
    }

    public int getDealerScore() {
        return dealer.calculateResultScore();
    }

    public List<Hand> getGamerHands() {
        return gamers.getHands();
    }
}
