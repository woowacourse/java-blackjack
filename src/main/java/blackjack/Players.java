package blackjack;

import java.util.ArrayList;
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

    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        names.add(this.dealer.getName());
        names.addAll(getEntryNames());
        return names;
    }

    public List<List<String>> getCardsToString() {
        List<String> dealerCardToString = new ArrayList<>();
        dealerCardToString.add(this.dealer.getFirstDeckToString());
        List<List<String>> cardsToString = new ArrayList<>();
        cardsToString.add(dealerCardToString);
        cardsToString.addAll(this.entries.getDecksToString());
        return cardsToString;
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

    public String getCurrentEntryName() {
        return this.entries.getCurrentEntryName();
    }

    public List<String> getCurrentDeckToString() {
        return this.entries.getCurrentDeckToString();
    }

    public int countCardsAddedToDealer() {
        return this.dealer.countAddedCards();
    }

    public boolean canDealerHit() {
        return this.dealer.canHit();
    }

    public List<Integer> getScores() {
        int dealerScore = this.dealer.getScore();
        List<Integer> playerScores = new ArrayList<>();
        playerScores.add(dealerScore);
        playerScores.addAll(entries.getScores());
        return playerScores;
    }
}
