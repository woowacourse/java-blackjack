package blackjack.model;

import blackjack.model.bet.Bet;
import blackjack.model.bet.Profits;
import blackjack.model.player.Dealer;
import blackjack.model.player.Entry;
import blackjack.model.player.Players;
import blackjack.model.trumpcard.TrumpCardPack;
import java.util.List;

public final class Game {
    private final Players players;
    private final TrumpCardPack trumpCardPack;

    public Game(List<String> names) {
        this.trumpCardPack = new TrumpCardPack();
        this.players = Players.from(names);
    }

    public void toNextEntry() {
        this.players.toNextEntry();
    }

    public boolean hasNextEntry() {
        return this.players.hasNextEntry();
    }

    public void betToCurrentEntry(int amount) {
        this.players.betToCurrent(Bet.from(amount));
    }

    public void toFirstEntry() {
        this.players.resetEntriesCursor();
    }

    public void giveFirstHands() {
        this.players.initializeHands(trumpCardPack::draw);
    }

    public void hitCurrentEntry() {
        this.players.addToCurrentEntry(trumpCardPack.draw());
    }

    public boolean canCurrentEntryHit() {
        return !this.players.isCurrentEntryBust();
    }

    public boolean hitDealer() {
        boolean dealerHit = false;
        while (this.players.canDealerHit()) {
            this.players.hitDealer(trumpCardPack.draw());
            dealerHit = true;
        }
        return dealerHit;
    }

    public Profits calculateProfits() {
        return this.players.calculateProfits();
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
