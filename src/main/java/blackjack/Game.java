package blackjack;

import java.util.List;

public class Game {
    private final Players players;
    private final TrumpCardPack trumpCardPack;

    public Game(List<String> names) {
        this.trumpCardPack = new TrumpCardPack();
        this.players = Players.from(names);
    }

    public void start() {
        this.players.giveFirstCards(trumpCardPack);
    }

    public boolean hasNextEntry() {
        return this.players.hasNextEntry();
    }

    public void toNextEntry() {
        this.players.toNextEntry();
    }

    public void hitCurrentEntry() {
        this.players.hitCurrentEntry(trumpCardPack.draw());
    }

    public boolean isCurrentEntryBust() {
        return this.players.isCurrentEntryBust();
    }

    public void hitDealer() {
        while (this.players.canDealerHit()) {
            this.players.hitDealer(trumpCardPack.draw());
        }
    }

    public int countCardsAddedToDealer() {
        return this.players.countCardsAddedToDealer();
    }

    public List<Entry> getEntries() {
        return this.players.getEntries();
    }

    public Dealer getDealer() {
        return this.players.getDealer();
    }

    public Entry getCurrentEntry() {
        return this.players.getCurrentEntry();
    }
}
