package controller;

import domain.Command;
import domain.GameManager;
import domain.betting.BettingAmount;
import domain.betting.BettingTable;
import domain.betting.ParticipantsProfitResult;
import domain.betting.PlayerBetting;
import domain.betting.PlayerBettingResult;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import view.BettingAmountParser;
import view.InputView;
import view.OutputView;
import view.PlayerParser;
import view.dto.InitialDealingResult;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final GameManager gameManager;

    public GameController(InputView inputView, OutputView outputView, GameManager manager) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameManager = manager;
    }

    public void run() {
        Players players = readPlayers();
        Dealer dealer = new Dealer();
        Participants participants = Participants.of(dealer, players);
        BettingTable bettingTable = placeBet(players);

        readyPhase(participants);
        playPhase(dealer, players);
        bettingResultPhase(bettingTable, participants);
    }

    private BettingTable placeBet(Players players) {
        BettingTable bettingTable = BettingTable.create();
        for (Player player : players) {
            BettingAmount bettingAmount = readBettingAmountFor(player);

            PlayerBetting playerBetting = PlayerBetting.from(player, bettingAmount);
            bettingTable.add(playerBetting);
        }

        return bettingTable;
    }

    private void readyPhase(Participants participants) {
        gameManager.dealInitialCards(participants);

        InitialDealingResult initialDealingResult = InitialDealingResult.from(participants);
        outputView.printInitialDealingResult(initialDealingResult);
    }

    private void playPhase(Dealer dealer, Players players) {
        playPlayersTurn(players);
        playDealerTurn(players, dealer);
    }

    private void playPlayersTurn(Players players) {
        for (Player player : players) {
            playPlayerTurn(player);
        }
        outputView.printNewLine();
    }

    private void playDealerTurn(Players players, Dealer dealer) {
        while (dealer.canReceive()) {
            gameManager.dealCard(dealer);
            outputView.printCompleteDealerTurn();
        }
        outputView.printNewLine();

        outputView.printParticipantResult(dealer.name(), gameManager.getCardsResult(dealer).getFormattedCards(),
                dealer.getScore());
        for (Player player : players) {
            outputView.printParticipantResult(player.name(), gameManager.getCardsResult(player).getFormattedCards(),
                    player.getScore());
        }
        outputView.printNewLine();
    }

    private void bettingResultPhase(BettingTable bettingTable, Participants participants) {
        List<PlayerBettingResult> playerBettingResults = participants.playersBettingResult();
        ParticipantsProfitResult participantsProfitResult = bettingTable.calculateAllParticipantsProfit(
                playerBettingResults);
        outputView.printParticipantsProfit(participantsProfitResult);
    }

    private BettingAmount readBettingAmountFor(Player player) {
        String rawBettingAmount = inputView.askBettingAmount(player);
        return BettingAmountParser.parse(rawBettingAmount);
    }


    private void playPlayerTurn(Player player) {
        while (shouldDrawCard(player)) {
            drawAndPrint(player);
        }
    }

    private boolean shouldDrawCard(Player player) {
        if (!player.canReceive()) {
            return false;
        }
        return isUserWantHit(player);
    }

    private boolean isUserWantHit(Player player) {
        Command command = Command.from(inputView.askPlayHit(player.name()));
        return !command.isNo();
    }

    private void drawAndPrint(Player player) {
        gameManager.dealCard(player);
        outputView.printParticipantCard(player.name(),
                gameManager.getCardsResult(player).getFormattedCards());
    }

    private Players readPlayers() {
        String rawPlayerNames = inputView.readPlayerNames();
        return PlayerParser.parseToPlayers(rawPlayerNames);
    }
}
