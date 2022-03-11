package blackjack.model;

import blackjack.model.player.Dealer;
import blackjack.model.player.Entry;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.model.trumpcard.TrumpCardPack;
import java.util.List;

public class Game {
    private static final int FIRST_DECK_SIZE = 2;

    private final Players players;
    private final TrumpCardPack trumpCardPack;

    public Game(List<String> names) {
        this.trumpCardPack = new TrumpCardPack();
        this.players = Players.from(names);
    }

    public void start() {
        for (int i = 0; i < FIRST_DECK_SIZE; i++) {
            this.players.operateToEach(this::giveCardTo);
        }
    }

    public void giveCardTo(Player player) {
        player.addCard(trumpCardPack.draw());
    }

    public boolean hasNextEntry() {
        return this.players.hasNextEntry();
    }

    public void toNextEntry() {
        this.players.toNextEntry();
    }

    public void hitCurrentEntry() {
        this.players.addToCurrentEntry(trumpCardPack.draw());
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
        return this.players.getDealerDeckSize() - FIRST_DECK_SIZE;
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

    public Results getResults() {
        return this.players.getResults();
    }
}
