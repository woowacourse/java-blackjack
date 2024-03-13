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
        OutputView.printDealerDealCard(dealer.getCards());
        players.forEach(player ->
                OutputView.printDealCards(player.getName(), player.getCards()));
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

        boolean isRun = true;
        while (isRun) {
            PlayerCommand command = requestUntilValid(() ->
                    PlayerCommand.from(InputView.readPlayerCommand(player.getName())));
            blackjackGame.hitOrStand(player, command);
            isRun = blackjackGame.isPlayerCanHit(player, command);
            printDealCards(player, command);
        }
    }

    private void printDealCards(Player player, PlayerCommand command) {
        if (command == PlayerCommand.STAND && player.getCards().size() == 2) {
            OutputView.printDealCards(player.getName(), player.getCards());
            return;
        }
        OutputView.printDealCards(player.getName(), player.getCards());
    }

    private void drawToDealer(Dealer dealer, BlackjackGame blackjackGame) {
        while (dealer.isScoreUnderBound()) {
            blackjackGame.drawIfScoreUnderBound(dealer);
            OutputView.printDealerHitAnnounce();
        }
    }

    private void printAllPlayersHandResult(Dealer dealer, Players players) {
        OutputView.printDealerCards(dealer.getCards(), dealer.getScore());
        players.forEach(player ->
                OutputView.printPlayerCards(player.getName(), player.getCards(), player.getScore()));
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
