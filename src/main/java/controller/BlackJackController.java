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

        Judge judge = createJudge(game);
        game.settleRoundBets(judge, bettingTable);
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

        while (player.canHit() && wantsToHit(player, hasPrintHand)) {
            game.hitPlayer(player);
            OutputView.printHandOutput(OutputDtoAssembler.toPlayerHandDto(player));
            hasPrintHand = true;
        }
    }

    private boolean wantsToHit(Player player, boolean hasPrintHand) {
        String yesNoInput = InputView.askPlayerCommand(player.getName());

        if (yesNoInput.equals("n")) {
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
        while (game.dealerShouldHit()) {
            OutputView.printDealerHitMessage();
            game.hitDealer();
        }
    }

    private Game createGame() {
        List<String> playerNames = InputView.askPlayerNames();
        return new Game(playerNames, new Deck(CardFactory.createDeck()));
    }

    private Judge createJudge(Game game) {
        return Judge.from(game.getDealer(), game.getPlayers());
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
