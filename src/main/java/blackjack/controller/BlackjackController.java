package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Names;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.money.Chip;
import blackjack.domain.money.PlayersProfit;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayerCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BlackjackController {

    public void run() {
        BlackjackGame blackjackGame = new BlackjackGame(Deck.make());
        Dealer dealer = new Dealer(new Chip(0L));
        Players players = createPlayers();

        dealAndPrintHand(blackjackGame, dealer, players);

        processHitOrStand(players, blackjackGame);
        drawToDealer(dealer, blackjackGame);
        printResultHand(dealer, players);

        calculateGameResultPrint(dealer, players);
    }

    private Players createPlayers() {
        Names names = new Names(InputView.readPlayersName());
        List<Player> makePlayers = new ArrayList<>();
        for (Name name : names.names()) {
            Chip chip = new Chip((long) InputView.readBettingChip(name.name()));
            makePlayers.add(new Player(name, chip));
        }

        return new Players(makePlayers);
    }

    private void dealAndPrintHand(BlackjackGame blackjackGame, Dealer dealer, Players players) {
        blackjackGame.deal(dealer, players);

        OutputView.printDealAnnounce(players.names());
        OutputView.printDealerDealCard(dealer.findPublicDealCard());

        for (Player player : players.values()) {
            OutputView.printDealCards(player.name(), player.cards());
        }
    }

    private void processHitOrStand(Players players, BlackjackGame blackjackGame) {
        for (Player player : players.values()) {
            askToPlayerHit(player, blackjackGame);
        }
    }

    private void askToPlayerHit(Player player, BlackjackGame blackjackGame) {
        boolean isRun = blackjackGame.isPlayerCanHit(player);
        while (isRun) {
            PlayerCommand command =
                    PlayerCommand.from(InputView.readPlayerCommand(player.name()));
            hitOrStandAndPrint(blackjackGame, player, command);
            isRun = isRun(blackjackGame, player, command);
        }
    }

    private boolean isRun(BlackjackGame blackjackGame, Player player, PlayerCommand command) {
        if (command == PlayerCommand.STAND) {
            return false;
        }
        return blackjackGame.isPlayerCanHit(player);
    }

    private void hitOrStandAndPrint(BlackjackGame blackjackGame, Player player, PlayerCommand command) {
        if (command == PlayerCommand.HIT) {
            blackjackGame.hit(player);
            OutputView.printDealCards(player.name(), player.cards());
        }
        if (command == PlayerCommand.STAND && player.isOnlyDeal()) {
            OutputView.printDealCards(player.name(), player.cards());
        }
    }

    private void drawToDealer(Dealer dealer, BlackjackGame blackjackGame) {
        int count = blackjackGame.drawUntilOverBoundWithCount(dealer);
        IntStream.range(0, count)
                .forEach(i -> OutputView.printDealerHitAnnounce());
    }

    private void printResultHand(Dealer dealer, Players players) {
        OutputView.printDealerCards(dealer.cards(), dealer.score().toInt());

        for (Player player : players.values()) {
            OutputView.printPlayerCards(player.name(), player.cards(), player.score().toInt());
        }
    }

    private void calculateGameResultPrint(Dealer dealer, Players players) {
        PlayersProfit playersProfit = players.calculateProfit(dealer);
        double dealerProfit = dealer.calculateProfit(playersProfit.allProfit());

        OutputView.printFinalProfitAnnounce();
        OutputView.printDealerProfit(dealerProfit);

        for (Player player : players.values()) {
            OutputView.printPlayerProfit(player.name(), playersProfit.findProfitBy(player));
        }
    }
}
