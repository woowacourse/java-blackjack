package blackjack.controller;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Referee;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Names;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.supplies.Chip;
import blackjack.domain.supplies.Deck;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayerCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BlackjackController {

    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Dealer dealer = new Dealer(new Chip(0));
        Names names = new Names(InputView.readPlayersName());

        List<Player> makePlayers = new ArrayList<>();
        for (Name name : names.names()) {
            Chip chip = new Chip(InputView.readBettingChip(name.name()));
            makePlayers.add(new Player(name, chip));
        }
        Players players = new Players(makePlayers);

        dealAndPrintResult(blackjackGame, dealer, players);

        askToPlayersHit(players, blackjackGame);
        drawToDealer(dealer, blackjackGame);
        printAllPlayersHandResult(dealer, players);

        calculateGameResultAndPrint(dealer, players);
    }

    private void dealAndPrintResult(BlackjackGame blackjackGame, Dealer dealer, Players players) {
        blackjackGame.deal(dealer, players);

        OutputView.printDealAnnounce(players.getNames());
        OutputView.printDealerDealCard(dealer.findPublicDealCard());

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

        boolean isRun = true;
        while (isRun) {
            PlayerCommand command = PlayerCommand.from(InputView.readPlayerCommand(player.getName()));
            hitOrStandAndPrint(blackjackGame, player, command);
            isRun = isRun(blackjackGame, player, command);
        }
    }

    private void hitOrStandAndPrint(BlackjackGame blackjackGame, Player player, PlayerCommand command) {
        if (command == PlayerCommand.HIT) {
            blackjackGame.hit(player);
            printCardsWhenHit(player);
        }
        if (command == PlayerCommand.STAND) {
            printCardsWhenStand(player);
        }
    }

    private void printCardsWhenHit(Player player) {
        OutputView.printDealCards(player.getName(), player.getCards());
    }

    private void printCardsWhenStand(Player player) {
        if (player.isOnlyDeal()) {
            OutputView.printDealCards(player.getName(), player.getCards());
        }
    }

    private boolean isRun(BlackjackGame blackjackGame, Player player, PlayerCommand command) {
        if (command == PlayerCommand.STAND) {
            return false;
        }
        return blackjackGame.isPlayerCanHit(player);
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
        //referee.calculatePlayersResults(players, dealer);
    }
}
