package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.game.DeckMachine;
import blackjack.domain.game.ResultJudge;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Names;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.money.Chip;
import blackjack.domain.money.ProfitCalculator;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.PlayerCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BlackjackController {

    public void run() {
        DeckMachine deckMachine = new DeckMachine(Deck.make());
        Dealer dealer = new Dealer(new Chip(0));
        Players players = createPlayers();

        dealAndPrintHand(deckMachine, dealer, players);

        processHitOrStand(players, deckMachine);
        drawToDealer(dealer, deckMachine);
        printResultHand(dealer, players);

        calculateGameResult(dealer, players);
        printProfit(dealer, players);
    }

    private Players createPlayers() {
        Names names = new Names(InputView.readPlayersName());
        List<Player> makePlayers = new ArrayList<>();
        for (Name name : names.names()) {
            Chip chip = new Chip(InputView.readBettingChip(name.name()));
            makePlayers.add(new Player(name, chip));
        }

        return new Players(makePlayers);
    }

    private void dealAndPrintHand(DeckMachine deckMachine, Dealer dealer, Players players) {
        deckMachine.deal(dealer, players);

        OutputView.printDealAnnounce(players.names());
        OutputView.printDealerDealCard(dealer.findPublicDealCard());

        for (Player player : players.values()) {
            OutputView.printDealCards(player.name(), player.cards());
        }
    }

    private void processHitOrStand(Players players, DeckMachine deckMachine) {
        for (Player player : players.values()) {
            askToPlayerHit(player, deckMachine);
        }
    }

    private void askToPlayerHit(Player player, DeckMachine deckMachine) {
        boolean isRun = deckMachine.isPlayerCanHit(player);
        while (isRun) {
            PlayerCommand command =
                    PlayerCommand.from(InputView.readPlayerCommand(player.name()));
            hitOrStandAndPrint(deckMachine, player, command);
            isRun = isRun(deckMachine, player, command);
        }
    }

    private boolean isRun(DeckMachine deckMachine, Player player, PlayerCommand command) {
        if (command == PlayerCommand.STAND) {
            return false;
        }
        return deckMachine.isPlayerCanHit(player);
    }

    private void hitOrStandAndPrint(DeckMachine deckMachine, Player player, PlayerCommand command) {
        if (command == PlayerCommand.HIT) {
            deckMachine.hit(player);
            OutputView.printDealCards(player.name(), player.cards());
        }
        if (command == PlayerCommand.STAND && player.isOnlyDeal()) {
            OutputView.printDealCards(player.name(), player.cards());
        }
    }

    private void drawToDealer(Dealer dealer, DeckMachine deckMachine) {
        int count = deckMachine.drawUntilOverBoundWithCount(dealer);
        IntStream.range(0, count)
                .forEach(i -> OutputView.printDealerHitAnnounce());
    }

    private void printResultHand(Dealer dealer, Players players) {
        OutputView.printDealerCards(dealer.cards(), dealer.score());

        for (Player player : players.values()) {
            OutputView.printPlayerCards(player.name(), player.cards(), player.score());
        }
    }

    private void calculateGameResult(Dealer dealer, Players players) {
        ResultJudge resultJudge = new ResultJudge();
        ProfitCalculator calculator = new ProfitCalculator();

        players.calculateProfit(dealer, resultJudge, calculator);
        calculator.dealerProfit(dealer, players);
    }

    private void printProfit(Dealer dealer, Players players) {
        OutputView.printFinalProfitAnnounce();
        OutputView.printDealerProfit(dealer.profit());

        for (Player player : players.values()) {
            OutputView.printPlayerProfit(player.name(), player.profit());
        }
    }
}
