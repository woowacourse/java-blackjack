package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.RandomShuffleStrategy;
import blackjack.domain.gamer.dealer.Dealer;
import blackjack.domain.gamer.player.Player;
import blackjack.domain.gamer.player.Players;
import blackjack.domain.money.Money;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayerCommand;

import java.util.Optional;
import java.util.function.Supplier;

public class BlackjackController {

    public void run() {
        Deck deck = createDeckWithRandomShuffle();
        Dealer dealer = new Dealer(deck);
        Players players = requestPlayers(deck);

        requestBetting(dealer, players);
        processDeal(dealer, players);
        processHitOrStand(dealer, players);
        printResult(dealer, players);
    }

    private Deck createDeckWithRandomShuffle() {
        return new Deck(new RandomShuffleStrategy());
    }

    private Players requestPlayers(Deck deck) {
        return requestUntilValid(() -> Players.of(InputView.readPlayersName(), deck));
    }

    private void requestBetting(Dealer dealer, Players players) {
        for (Player player : players.get()) {
            String name = player.getName();
            Money money = requestUntilValid(() -> Money.from(InputView.readPlayerBetting(name)));
            dealer.keepPlayerMoney(name, money);
        }
    }

    private void processDeal(Dealer dealer, Players players) {
        OutputView.printDealAnnounce(players.getNames());
        dealer.deal();
        players.deal();

        OutputView.printCard(dealer.getName(), dealer.getFirstCard());
        players.get()
                .forEach(player -> OutputView.printCards(player.getName(), player.getCards()));
        OutputView.printNewLine();
    }

    private void processHitOrStand(Dealer dealer, Players players) {
        players.get()
                .forEach(this::requestHitOrStand);
        OutputView.printNewLine();
        dealerHitUntilBound(dealer);

        OutputView.printCardsWithScore(dealer.getName(), dealer.getCards(), dealer.getScore());
        players.get().forEach(player ->
                OutputView.printCardsWithScore(player.getName(), player.getCards(), player.getScore()));
    }

    private void requestHitOrStand(Player player) {
        PlayerCommand playerCommand;
        do {
            playerCommand = requestUntilValid(() ->
                    PlayerCommand.from(InputView.readPlayerCommand(player.getName())));

            if (playerCommand == PlayerCommand.STAND) {
                printIfPlayerOnlyDeal(player);
                break;
            }
            hitAndPrint(player);
        } while (player.canContinue());
    }

    private void printIfPlayerOnlyDeal(Player player) {
        if (player.isOnlyDeal()) {
            OutputView.printCards(player.getName(), player.getCards());
        }
    }

    private void hitAndPrint(Player player) {
        player.hit();
        OutputView.printCards(player.getName(), player.getCards());
    }

    private void dealerHitUntilBound(Dealer dealer) {
        while (dealer.canContinue()) {
            dealer.hit();
            OutputView.printDealerHitAnnounce();
        }
    }

    private void printResult(Dealer dealer, Players players) {
        OutputView.printResult(
                dealer.getName(),
                dealer.calculateDealerRevenue(players),
                dealer.calculatePlayerRevenues(players));
    }

    private <T> T requestUntilValid(Supplier<T> supplier) {
        Optional<T> result = Optional.empty();
        while (result.isEmpty()) {
            result = tryGet(supplier);
        }
        return result.get();
    }

    private <T> Optional<T> tryGet(Supplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return Optional.empty();
        }
    }
}
