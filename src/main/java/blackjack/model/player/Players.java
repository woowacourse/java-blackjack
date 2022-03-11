package blackjack.model.player;

import blackjack.model.Results;
import blackjack.model.trumpcard.TrumpCard;
import java.util.List;
import java.util.function.Consumer;

public class Players {
    private final Entries entries;
    private final Dealer dealer;

    private Players(Entries entries, Dealer dealer) {
        this.entries = entries;
        this.dealer = dealer;
    }

    public static Players from(List<String> names) {
        return new Players(Entries.from(names), new Dealer());
    }

    public void operateToEach(Consumer<Player> consumer) {
        consumer.accept(dealer);
        this.entries.operateToEach(consumer);
    }

    public boolean hasNextEntry() {
        return !this.entries.hasNoNext();
    }

    public void toNextEntry() {
        this.entries.toNextEntry();
    }

    public void addToCurrentEntry(TrumpCard card) {
        this.entries.addToCurrentEntry(card);
    }

    public boolean isCurrentEntryBust() {
        return this.entries.isCurrentEntryBust();
    }

    public void hitDealer(TrumpCard card) {
        this.dealer.hit(card);
    }

    public boolean canDealerHit() {
        return this.dealer.canHit();
    }

    public List<Entry> getEntries() {
        return this.entries.getValues();
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public int getDealerDeckSize() {
        return this.dealer.getDeckSize();
    }

    public Entry getCurrentEntry() {
        return this.entries.getCurrentEntry();
    }

    public Results getResults() {
        return this.entries.compareAllWith(dealer);
    }
}
