package controller;

import domain.Player;
import domain.Deck;
import domain.Participants;
import domain.Result;
import domain.RandomShuffleStrategy;
import domain.Participant;

import service.GameService;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

public class BlackJackController {

    private static final int INITIAL_CARD_COUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        Deck deck = Deck.from(new RandomShuffleStrategy());
        Participants participants = makeParticipants();
        GameService gameService = new GameService(deck, participants);

        initGameStatus(gameService);
        play(participants, gameService);
        showResults(gameService);
    }

    private Participants makeParticipants() {
        try {
            return Participants.from(inputView.requestNames());
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return makeParticipants();
        }
    }

    private void initGameStatus(GameService gameService) {
        init(gameService);
        printInitHands(gameService);
    }

    private void init(GameService gameService) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            gameService.dealCardsToParticipants();
        }

        outputView.printInitializingFinishMessage(gameService.getParticipantsNames());
    }

    private void printInitHands(GameService gameService) {
        for (Map.Entry<String, List<String>> participantHand : gameService.getParticipantsInitHands().entrySet()) {
            outputView.printParticipantCard(participantHand.getKey(), participantHand.getValue());
        }
        outputView.printEmptyLine();
    }

    private void play(Participants participants, GameService gameService) {
        hitOrStayForEachPlayer(participants, gameService);
        hitOrStayForDealer(gameService);
    }

    private void hitOrStayForEachPlayer(Participants participants, GameService gameService) {
        for (Player player : participants.findPlayers()) {
            keepHitOrStay(gameService, player);
        }
    }

    private void keepHitOrStay(GameService gameService, Player player) {
        try {
            hitOrStay(gameService, player);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            keepHitOrStay(gameService, player);
        }
    }

    private void hitOrStay(GameService gameService, Player player) {
        while (gameService.isHit(inputView.requestDrawingCard(player.getName()))) {
            gameService.hit(player);
            printIfPoolDoesNotContainsHand(player, gameService);
        }
        printIfPoolDoesNotContainsHand(player, gameService);
    }

    private void printIfPoolDoesNotContainsHand(Player player, GameService gameService) {
        if (!gameService.existHandInPool(player.getCardNames())) {
            gameService.addHandToPool(player.getCardNames());
            outputView.printParticipantCard(player.getName(), player.getCardNames());
        }
    }

    private void hitOrStayForDealer(GameService gameService) {
        while (gameService.isDealerHandValueUnderStandard()) {
            gameService.dealerHit();
            outputView.printDealerPickCardMessage();
        }
    }

    private void showResults(GameService gameService) {
        printScores(gameService);
        printResults(gameService);
    }

    private void printScores(GameService gameService) {
        for (Map.Entry<Participant, Boolean> participantScore : gameService.getParticipantIsBust().entrySet()) {
            outputView.printParticipantHandValue(participantScore.getKey().getName(),
                participantScore.getKey().getCards(), participantScore.getValue());
        }
    }

    private void printResults(GameService gameService) {
        outputView.printResultInfo();

        Map<String, Result> playerResults = gameService.calculatePlayerResults();
        Map<Result, Integer> dealerResults = gameService.calculateDealerResults(playerResults);
        outputView.printDealerResult(dealerResults);
        for (Map.Entry<String, Result> playerResult : playerResults.entrySet()) {
            outputView.printPlayerResult(playerResult.getKey(), playerResult.getValue());
        }
    }
}
