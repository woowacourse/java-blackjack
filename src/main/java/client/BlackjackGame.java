package client;

import participant.GameTable;
import participant.Players;
import participant.Dealer;
import participant.Player;
import participant.value.Money;
import result.AllPlayerResult;

public class BlackjackGame {
    private final InputProcessor inputProcessor;
    private final OutputPrinter outputPrinter;

    public BlackjackGame(InputProcessor inputProcessor, OutputPrinter outputPrinter) {
        this.inputProcessor = inputProcessor;
        this.outputPrinter = outputPrinter;
    }

    public void execute() {
        final Dealer dealer = Dealer.createWithNoHand();

        Players playersBeforeBetting = Players.createByNames(inputProcessor.requestPlayerNames());
        final Players players = playersBeforeBetting.bet(player ->
                Money.bet(inputProcessor.requestBettingPrice(player)));

        final GameTable gameTable = GameTable.enter(dealer, players);

        outputPrinter.printInitialHand(gameTable.openDealerHand(), gameTable.openPlayersHand());

        gameTable.playPlayersRound(this::requestAdditionalCard);
        gameTable.playDealerRound();

        outputPrinter.printFinalDealerHand(gameTable.openDealerFinalHand(), gameTable.openDealerFinalScore());
        outputPrinter.printFinalPlayersHand(gameTable.openPlayersFinalHand());

        AllPlayerResult allPlayerResult = gameTable.calculateAllPlayerResult();
        outputPrinter.printAllPlayerResult(allPlayerResult);
    }

    private AnswerType requestAdditionalCard(Player player) {
        outputPrinter.printCurrentCard(player);

        if (player.isBust()) {
            outputPrinter.printBustMessage();
            return AnswerType.NO;
        }

        return inputProcessor.requestAdditionalCard(player);
    }
}
