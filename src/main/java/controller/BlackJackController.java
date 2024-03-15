package controller;

import domain.*;
import dto.ParticipantHandDto;
import dto.ParticipantSettlementDto;
import dto.ParticipantsHandDto;
import dto.PlayersNameDto;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

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
        BettingPot bettingPot = initBettingPot(players);
        BlackJackGame blackJackGame = initBlackJackGame(players);

        printInitialDealMessage(blackJackGame);
        printInitialHand(blackJackGame);
        repeatHitUntilStand(blackJackGame);

        printGameResult(blackJackGame);
        printSettlement(blackJackGame, bettingPot);
    }

    private Players readPlayers() {
        List<String> inputNames = inputView.readPlayerNames();

        return new Players(inputNames);
    }

    private BettingPot initBettingPot(Players players) {
        BettingPot bettingPot = new BettingPot();
        List<Player> everyPlayers = players.getPlayers();

        for (Player player : everyPlayers) {
            int betAmount = inputView.readBetAmount(player.getName());
            Bet bet = new Bet(betAmount);
            bettingPot.collect(player, bet);
        }

        return bettingPot;
    }

    private BlackJackGame initBlackJackGame(Players players) {
        CardDeck cardDeck = CardDeck.of(cardShuffleStrategy);
        Dealer dealer = new Dealer(cardDeck);

        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);
        blackJackGame.initHand();

        return blackJackGame;
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
        outputView.printFinalHandAndScore(participantHandDtos);
    }

    private void printSettlement(BlackJackGame blackJackGame, BettingPot bettingPot) {
        Map<Player, Result> gameResults = blackJackGame.getGameResults();
        Map<Player, Integer> playerSettlement = bettingPot.settlePlayer(gameResults);
        int dealerSettlement = bettingPot.settleDealer(gameResults);

        ParticipantSettlementDto participantSettlementDto
                = ParticipantSettlementDto.of(Dealer.DEALER_NAME, dealerSettlement, playerSettlement);
        outputView.printSettlement(participantSettlementDto);
    }
}
