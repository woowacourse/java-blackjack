package blackjack.model;

import blackjack.model.bet.Bet;
import blackjack.model.bet.Profits;
import blackjack.model.player.Dealer;
import blackjack.model.player.Entry;
import blackjack.model.player.Players;
import blackjack.model.trumpcard.TrumpCardPack;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Game {
    private final Players players;
    private final TrumpCardPack trumpCardPack;

    public Game(List<String> names) {
        this.trumpCardPack = new TrumpCardPack();
        this.players = Players.from(names);
    }

    public void betMoney(Function<Game, Integer> betInputFunction) {
        do {
            this.players.toNextEntry();
            betToCurrentEntry(betInputFunction);
        } while (this.players.hasNextEntry());
    }

    private void betToCurrentEntry(Function<Game, Integer> betInputFunction) {
        int amount = betInputFunction.apply(this);
        this.players.betToCurrent(Bet.from(amount));
    }

    public void start(Consumer<Game> firstHandsPrinter) {
        this.players.initializeHands(trumpCardPack::draw);
        firstHandsPrinter.accept(this);
    }

    public void playEntries(Predicate<Game> hitPredicate,
                            Consumer<Game> bustMessagePrinter, Consumer<Game> fullHandPrinter) {
        this.players.resetEntriesCursor();
        do {
            this.players.toNextEntry();
            playTurn(hitPredicate, bustMessagePrinter, fullHandPrinter);
        } while (this.players.hasNextEntry());
    }

    private void playTurn(Predicate<Game> hitPredicate,
                          Consumer<Game> bustMessagePrinter, Consumer<Game> fullHandPrinter) {
        if (this.players.isCurrentEntryBust()) {
            bustMessagePrinter.accept(this);
            return;
        }
        if (!hitPredicate.test(this)) {
            fullHandPrinter.accept(this);
            return;
        }
        hitCurrentEntry(hitPredicate, bustMessagePrinter, fullHandPrinter);
    }

    private void hitCurrentEntry(Predicate<Game> hitPredicate,
                                 Consumer<Game> bustMessagePrinter, Consumer<Game> fullHandPrinter) {
        this.players.addToCurrentEntry(trumpCardPack.draw());
        fullHandPrinter.accept(this);
        playTurn(hitPredicate, bustMessagePrinter, fullHandPrinter);
    }

    public void showResults(Consumer<Game> dealerAddedCountPrinter, Consumer<Game> profitsPrinter) {
        playDealer(dealerAddedCountPrinter);
        showProfits(profitsPrinter);
    }

    private void playDealer(Consumer<Game> dealerAddedCountPrinter) {
        hitDealer();
        if (countCardsAddedToDealer() > 0) {
            dealerAddedCountPrinter.accept(this);
        }
    }

    private void hitDealer() {
        while (this.players.canDealerHit()) {
            this.players.hitDealer(trumpCardPack.draw());
        }
    }

    private void showProfits(Consumer<Game> profitsPrinter) {
        profitsPrinter.accept(this);
    }

    public int countCardsAddedToDealer() {
        return this.players.countCardsAddedToDealer();
    }

    public Profits getProfits() {
        return this.players.getProfits();
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
