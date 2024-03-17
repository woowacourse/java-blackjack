package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.game.DeckMachine;
import blackjack.domain.game.PlayerResult;
import blackjack.domain.game.Referee;
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
        Names names = new Names(InputView.readPlayersName());

        List<Player> makePlayers = new ArrayList<>();
        for (Name name : names.names()) {
            Chip chip = new Chip(InputView.readBettingChip(name.name()));
            makePlayers.add(new Player(name, chip));
        }
        Players players = new Players(makePlayers);

        dealAndPrintResult(deckMachine, dealer, players);

        askToPlayersHit(players, deckMachine);
        drawToDealer(dealer, deckMachine);
        printAllPlayersHandResult(dealer, players);

        calculateGameResult(dealer, players);

        // TODO: 점수계산
        OutputView.printFinalProfitAnnounce();
        OutputView.printDealerProfit(dealer.profit());

        for (Player player : players.values()) {
            OutputView.printPlayerProfit(player.name(), player.profit());
        }
    }

    private void dealAndPrintResult(DeckMachine deckMachine, Dealer dealer, Players players) {
        deckMachine.deal(dealer, players);

        OutputView.printDealAnnounce(players.names());
        OutputView.printDealerDealCard(dealer.findPublicDealCard());

        for (Player player : players.values()) {
            OutputView.printDealCards(player.name(), player.cards());
        }
    }

    private void askToPlayersHit(Players players, DeckMachine deckMachine) {
        for (Player player : players.values()) {
            processHitOrStand(player, deckMachine);
        }
    }

    private void processHitOrStand(Player player, DeckMachine deckMachine) {
        if (player.isBlackjack()) {
            return;
        }

        boolean isRun = true;
        while (isRun) {
            PlayerCommand command = PlayerCommand.from(InputView.readPlayerCommand(player.name()));
            hitOrStandAndPrint(deckMachine, player, command);
            isRun = isRun(deckMachine, player, command);
        }
    }

    private void hitOrStandAndPrint(DeckMachine deckMachine, Player player, PlayerCommand command) {
        if (command == PlayerCommand.HIT) {
            deckMachine.hit(player);
            printCardsWhenHit(player);
        }
        if (command == PlayerCommand.STAND) {
            printCardsWhenStand(player);
        }
    }

    private void printCardsWhenHit(Player player) {
        OutputView.printDealCards(player.name(), player.cards());
    }

    private void printCardsWhenStand(Player player) {
        if (player.isOnlyDeal()) {
            OutputView.printDealCards(player.name(), player.cards());
        }
    }

    private boolean isRun(DeckMachine deckMachine, Player player, PlayerCommand command) {
        if (command == PlayerCommand.STAND) {
            return false;
        }
        return deckMachine.isPlayerCanHit(player);
    }

    private void drawToDealer(Dealer dealer, DeckMachine deckMachine) {
        int count = deckMachine.drawUntilOverBoundWithCount(dealer);
        IntStream.range(0, count).forEach(i -> OutputView.printDealerHitAnnounce());
    }

    private void printAllPlayersHandResult(Dealer dealer, Players players) {
        OutputView.printDealerCards(dealer.cards(), dealer.score());

        for (Player player : players.values()) {
            OutputView.printPlayerCards(player.name(), player.cards(), player.score());
        }
    }

    private void calculateGameResult(Dealer dealer, Players players) {
        Referee referee = new Referee();
        ProfitCalculator calculator = new ProfitCalculator();

        for (Player player : players.values()) {
            PlayerResult result = referee.judgePlayerResult(dealer, player);
            calculator.playerProfit(player, result);
        }

        calculator.dealerProfit(dealer, players);
    }
}
