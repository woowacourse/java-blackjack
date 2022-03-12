package blackjack;

import blackjack.trumpcard.CardPack;

import java.util.List;

public class BlackJackGame {
    private static final int START_CARD_COUNT = 2;

    private final Player dealer;
    private final Gamers gamers;

    public BlackJackGame(List<String> names) {
        this.dealer = new Dealer();
        this.gamers = Gamers.from(names);
    }

    public void giveStartingCardsBy(CardPack cardPack) {
        giveCardsToDealerBy(cardPack);
        for (Player gamer : gamers.getValues()) {
            giveCardsTo(gamer, cardPack);
        }
    }

    private void giveCardsToDealerBy(CardPack cardPack) {
        for (int i = 0; i < START_CARD_COUNT; i++) {
            dealer.receive(cardPack.draw());
        }
    }

    private void giveCardsTo(Player gamer, CardPack cardPack) {
        for (int i = 0; i < START_CARD_COUNT; i++) {
            gamer.receive(cardPack.draw());
        }
    }

    public boolean isDrawPossible() {
        return gamers.hasNextGamer();
    }

    public boolean isBustOnNowTurn() {
        return gamers.isCurrentGamerBust();
    }

    public void drawCardFrom(CardPack cardPack) {
        gamers.giveCurrentGamer(cardPack.draw());
    }

    public void nextDrawTurn() {
        gamers.nextGamer();
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getGamers() {
        return gamers.getValues();
    }

    public Player getCurrentPlayer() {
        return gamers.getCurrentValue();
    }
/*
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
