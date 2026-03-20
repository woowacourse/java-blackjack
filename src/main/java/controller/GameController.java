package controller;

import domain.Command;
import domain.GameManager;
import domain.betting.BettingAmount;
import domain.betting.BettingTable;
import domain.betting.PlayerBet;
import domain.betting.PlayerMatchResult;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import view.BettingAmountParser;
import view.InputView;
import view.OutputView;
import view.PlayerParser;
import view.dto.InitialDealingResult;
import view.dto.ParticipantCards;
import view.dto.ParticipantsProfit;

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
        resultPhase(bettingTable, participants);
    }

    private BettingTable placeBet(Players players) {
        BettingTable bettingTable = BettingTable.create();
        for (Player player : players) {
            BettingAmount bettingAmount = readBettingAmountFor(player);

            PlayerBet playerBet = PlayerBet.from(player, bettingAmount);
            bettingTable.place(playerBet);
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
        playDealerTurn(dealer);
        printPlayResult(players, dealer);
    }

    private void playPlayersTurn(Players players) {
        for (Player player : players) {
            playPlayerTurn(player);
        }
        outputView.printNewLine();
    }

    private void playDealerTurn(Dealer dealer) {
        while (dealer.canReceive()) {
            gameManager.dealCard(dealer);
            outputView.printCompleteDealerTurn();
        }
        outputView.printNewLine();
    }

    private void printPlayResult(Players players, Dealer dealer) {
        outputView.printParticipantResult(dealer.name(), gameManager.getCardsResult(dealer).getFormattedCards(),
                dealer.getScore());
        for (Player player : players) {
            outputView.printParticipantResult(player.name(), gameManager.getCardsResult(player).getFormattedCards(),
                    player.getScore());
        }
        outputView.printNewLine();
    }

    private void resultPhase(BettingTable bettingTable, Participants participants) {
        List<PlayerMatchResult> playerMatchResults = participants.playersBettingResult();
        ParticipantsProfit participantsProfit = bettingTable.calculateAllParticipantsProfit(
                playerMatchResults);
        outputView.printParticipantsProfit(participantsProfit);
    }

    private BettingAmount readBettingAmountFor(Player player) {
        String rawBettingAmount = inputView.askBettingAmount(player);
        return BettingAmountParser.parse(rawBettingAmount);
    }


    private void playPlayerTurn(Player player) {
        while (shouldDrawCard(player)) {
            dealingTo(player);
            ParticipantCards participantCards = ParticipantCards.fromPlayer(player);
            outputView.printParticipantCards(participantCards);
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

    private void dealingTo(Participant participant) {
        gameManager.dealCard(participant);
    }

    private Players readPlayers() {
        String rawPlayerNames = inputView.readPlayerNames();
        return PlayerParser.parseToPlayers(rawPlayerNames);
    }
}
