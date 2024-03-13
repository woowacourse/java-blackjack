package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Referee;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayerCommand;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class BlackjackController {

    public void run() {
        Dealer dealer = new Dealer(new Gamer(new Hand(List.of())));
        Players players = requestUntilValid(() -> Players.from(InputView.readPlayersName()));
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());

        dealAndPrintResult(blackjackGame, dealer, players);

        askToPlayersHit(players, blackjackGame);
        drawToDealer(dealer, blackjackGame);
        printAllPlayersHandResult(dealer, players);

        calculateGameResultAndPrint(dealer, players);
    }

    private void dealAndPrintResult(BlackjackGame blackjackGame, Dealer dealer, Players players) {
        blackjackGame.deal(dealer, players);

        OutputView.printDealAnnounce(players.getNames());
        OutputView.printDealerDealCard(dealer.getFirstDealCard());

        for (Player player : players.getPlayers()) {
            OutputView.printDealCards(player.getName(), player.getCards());
        }
    }

    private void askToPlayersHit(Players players, BlackjackGame blackjackGame) {
        for (Player player : players.getPlayers()) {
            processHitOrStand(player, blackjackGame);
        }
    }

    private void processHitOrStand(Player player, BlackjackGame blackjackGame) {
        if (player.isBlackjack()) {
            return;
        }

        boolean canHit = true;
        while (canHit) {
            PlayerCommand command = requestUntilValid(() ->
                    PlayerCommand.from(InputView.readPlayerCommand(player.getName())));
            if (command == PlayerCommand.HIT) {
                blackjackGame.hit(player);
                printCardsWhenHit(player);
                canHit = blackjackGame.isPlayerCanHit(player);
            }
            if (command == PlayerCommand.STAND) {
                printCardsWhenStand(player);
                break;
            }
        }
    }

    private void printCardsWhenHit(Player player) {
        OutputView.printDealCards(player.getName(), player.getCards());
    }

    private void printCardsWhenStand(Player player) {
        if (player.getCards().size() == 2) {
            OutputView.printDealCards(player.getName(), player.getCards());
        }
    }

    private void drawToDealer(Dealer dealer, BlackjackGame blackjackGame) {
        int count = blackjackGame.drawUntilOverBoundWithCount(dealer);
        IntStream.range(0, count).forEach(i -> OutputView.printDealerHitAnnounce());
    }

    private void printAllPlayersHandResult(Dealer dealer, Players players) {
        OutputView.printDealerCards(dealer.getCards(), dealer.getScore());

        for (Player player : players.getPlayers()) {
            OutputView.printPlayerCards(player.getName(), player.getCards(), player.getScore());
        }
    }

    private void calculateGameResultAndPrint(Dealer dealer, Players players) {
        Referee referee = new Referee();
        referee.calculatePlayersResults(players, dealer);

        OutputView.printWinAnnounce();
        OutputView.printDealerWinStatus(referee.getDealerResult());
        referee.getPlayersNameAndResults().forEach(OutputView::printPlayerWinStatus);
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
