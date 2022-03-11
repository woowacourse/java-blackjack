package blackjack;

import blackjack.trumpcard.CardPack;

import java.util.List;

public class BlackJackGame {
    private static final int START_CARD_COUNT = 2;

    private final Player dealer;
    private final Entries entries;

    public BlackJackGame(List<String> names) {
        this.dealer = new Dealer();
        this.entries = Entries.from(names);
    }

    //TODO: 함수 네이밍 변경
    public void initGame(CardPack cardPack) {
        giveCardsToDealer(cardPack);
        for (Player entry : entries.getValues()) {
            giveCardsToEntry(entry, cardPack);
        }
    }

    private void giveCardsToDealer(CardPack cardPack) {
        for (int i = 0; i < START_CARD_COUNT; i++) {
            dealer.receiveCard(cardPack.draw());
        }
    }

    private void giveCardsToEntry(Player entry, CardPack cardPack) {
        for (int i = 0; i < START_CARD_COUNT; i++) {
            entry.receiveCard(cardPack.draw());
        }
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getEntries() {
        return entries.getValues();
    }
/*
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

    public List<String> getEntryNames() {
        return this.players.getEntryNames();
    }

    public List<String> getNames() {
        return this.players.getNames();
    }

    public List<List<String>> getDecksToString() {
        return this.players.getCardsToString();
    }

    public String getCurrentEntryName() {
        return this.players.getCurrentEntryName();
    }

    public List<String> getCurrentDeckToString() {
        return this.players.getCurrentDeckToString();
    }

    public int countCardsAddedToDealer() {
        return this.players.countCardsAddedToDealer();
    }

    public List<Integer> getScores() {
        return this.players.getScores();
    }

 */
}
