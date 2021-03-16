package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.player.*;
import blackjack.view.OutputView;

import java.util.stream.IntStream;

public class BlackJackGame {
    private static final int NUMBER_OF_INITIAL_CARDS = 2;

    private final Deck deck = new Deck();
    private final Dealer dealer = new Dealer();
    private final Gamblers gamblers = new Gamblers();

    public BlackJackGame() {

    }

    public void addPlayer(final Gambler newGambler, final int bettingMoney) {
        gamblers.add(newGambler);
        dealer.takeMoney(newGambler, new Money(bettingMoney));
    }

    public void distributeInitialCards() {
        giveCards(dealer, NUMBER_OF_INITIAL_CARDS);
        for (final Gambler gambler : gamblers) {
            giveCards(gambler, NUMBER_OF_INITIAL_CARDS);
        }
    }

    private void giveCards(final Player player, final int numberOfCards) {
        IntStream.range(0, numberOfCards)
                .forEach(i -> giveCard(player));
    }

    public void giveCard(final Player player) {
        player.receiveCard(deck.draw());
    }

    public void calculateMoney() {
        checkBlackJack();

        final WinningResult winningResult = calculateResult();
        for (final Gambler gambler : gamblers) {
            giveWinningMoney(gambler, winningResult.get(gambler));
        }
    }

    private void checkBlackJack() {
        for (final Gambler gambler : gamblers) {
            dealer.checkBlackJack(gambler);
        }
    }

    private void giveWinningMoney(final Gambler gambler, final WinOrLose winOrLose) {
        if (gambler.hasBlackJack()) {
            return;
        }
        dealer.calculateMoney(gambler, winOrLose);
    }

    public WinningResult calculateResult() {
        final WinningResult winningResult = new WinningResult();
        for (final Gambler gambler : gamblers) {
            winningResult.add(gambler, dealer.calculateWinOrLose(gambler));
        }
        return winningResult;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Gamblers getGamblers() {
        return gamblers;
    }
}
