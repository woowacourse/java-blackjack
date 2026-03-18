package controller;

import assembler.OutputDtoAssembler;
import domain.*;
import factory.CardFactory;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackController {

    public void run() {
        Game game = createGame();
        BettingTable bettingTable = createBettingTable(game);
        OutputView.printInitMessage(OutputDtoAssembler
                .toBlackJackInitStatusDto(game.getDealer(), game.getPlayers()));

        playPlayers(game);
        playDealer(game);

        Map<Player, WinningStatus> winningStatusMap = game.calculateAllResults();
        bettingTable.updateProfits(winningStatusMap);
        OutputView.printFinalResult(OutputDtoAssembler
                .toFinalResultDto(game.getDealer(), game.getPlayers(), bettingTable));
    }

    private void playPlayers(Game game) {
        for (Player player : game.getPlayers()) {
            playPlayer(game, player);
        }
    }

    private void playPlayer(Game game, Player player) {
        boolean hasPrintHand = false;

        while (player.canHit() && wantsToHit(game, player, hasPrintHand)) {
            game.hitPlayer(player);
            OutputView.printHandOutput(OutputDtoAssembler.toPlayerHandDto(player));
            hasPrintHand = true;
        }
    }

    private boolean wantsToHit(Game game, Player player, boolean hasPrintHand) {
        String yesNoInput = InputView.askPlayerCommand(player.getName());

        if (yesNoInput.equals("n")) {
            game.stayPlayer(player);
            printHandIfFirstTurn(player, hasPrintHand);
            return false;
        }
        return true;
    }

    private void printHandIfFirstTurn(Player player, boolean hasPrintHand) {
        if (!hasPrintHand) {
            OutputView.printHandOutput(OutputDtoAssembler.toPlayerHandDto(player));
        }
    }

    private void playDealer(Game game) {
        while (game.dealerCanHit()) {
            OutputView.printDealerHitMessage();
            game.hitDealer();
        }
        game.stayDealer();
    }

    private Game createGame() {
        List<Name> playerNames = InputView.askPlayerNames().stream()
                .map(Name::new)
                .toList();
        return new Game(playerNames, new Deck(CardFactory.createDeck()));
    }

    private BettingTable createBettingTable(Game game) {
        Map<Player, BetAmount> moneyTable = new LinkedHashMap<>();
        for (Player player : game.getPlayers()) {
            String moneyInput = InputView.askPlayerBettingMoney(player.getName());
            BetAmount amount = new BetAmount(moneyInput);
            moneyTable.put(player, amount);
        }
        return BettingTable.from(moneyTable);
    }
}
