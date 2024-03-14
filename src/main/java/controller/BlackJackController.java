package controller;

import domain.*;
import dto.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;
    private final CardShuffleStrategy cardShuffleStrategy;

    public BlackJackController(InputView inputView, OutputView outputView, CardShuffleStrategy cardShuffleStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardShuffleStrategy = cardShuffleStrategy;
    }

    public void run() {
        Players players = readPlayers();
        CardDeck cardDeck = CardDeck.of(cardShuffleStrategy);
        Dealer dealer = new Dealer(cardDeck);
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);
        blackJackGame.initHand();

        printInitialDealMessage(blackJackGame);
        printInitialHand(blackJackGame);
        repeatHitUntilStand(blackJackGame);
        printGameResult(blackJackGame);
    }

    private Players readPlayers() {
        List<String> inputNames = inputView.readPlayerNames();

        return new Players(inputNames);
    }

    private void printInitialDealMessage(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        PlayersNameDto playersNameDto = PlayersNameDto.from(players);
        outputView.printInitialDealMessage(playersNameDto);
    }

    private void printInitialHand(BlackJackGame blackJackGame) {
        List<Participant> initialParticipants = blackJackGame.getEveryParticipants();
        ParticipantsHandDto participantsHandDto = ParticipantsHandDto.from(initialParticipants);
        outputView.printInitialHand(participantsHandDto);
    }

    private void repeatHitUntilStand(BlackJackGame blackJackGame) {
        for (Player player : blackJackGame.getPlayers()) {
            repeatHitUntilPlayerStand(blackJackGame, player);
        }

        repeatHitUntilDealerStand(blackJackGame);
    }

    private void repeatHitUntilPlayerStand(BlackJackGame blackJackGame, Player player) {
        while (player.isHittable() && inputView.readCommand(player.getName()).equals("y")) {
            blackJackGame.hitParticipant(player);
            outputView.printHandAfterHit(ParticipantHandDto.from(player));
        }
    }

    private void repeatHitUntilDealerStand(BlackJackGame blackJackGame) {
        while (blackJackGame.isDealerHittable()) {
            blackJackGame.hitDealer();
            outputView.printDealerHitMessage();
        }
    }

    private void printGameResult(BlackJackGame blackJackGame) {
        ParticipantsHandDto participantHandDtos = ParticipantsHandDto.from(blackJackGame.getEveryParticipants());
        DealerWinLossDto dealerWinLossDto = DealerWinLossDto.from(blackJackGame.getGameResults());
        PlayersWinLossDto playersWinLossDto = PlayersWinLossDto.from(blackJackGame.getGameResults());

        outputView.printFinalHandAndScore(participantHandDtos);
        outputView.printWinLoss(dealerWinLossDto, playersWinLossDto);
    }
}
