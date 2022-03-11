package blackjack;

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

    public List<String> getEntryNames() {
        return this.entries.getNames();
    }
}
