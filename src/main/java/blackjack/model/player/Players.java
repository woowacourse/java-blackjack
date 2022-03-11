package blackjack.model.player;

import blackjack.model.trumpcard.TrumpCard;
import blackjack.model.trumpcard.TrumpCardPack;
import java.util.List;

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

    public void giveFirstCards(TrumpCardPack trumpCardPack) {
        this.entries.giveFirstCards(trumpCardPack);
        this.dealer.addCard(trumpCardPack.draw());
    }

    public boolean hasNextEntry() {
        return !this.entries.hasNoNext();
    }

    public void toNextEntry() {
        this.entries.toNextEntry();
    }

    public void hitCurrentEntry(TrumpCard card) {
        this.entries.hitCurrentEntry(card);
    }

    public boolean isCurrentEntryBust() {
        return this.entries.isCurrentEntryBust();
    }

    public void hitDealer(TrumpCard card) {
        this.dealer.hit(card);
    }

    public int countCardsAddedToDealer() {
        return this.dealer.countAddedCards();
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

    public Entry getCurrentEntry() {
        return this.entries.getCurrentEntry();
    }
}
