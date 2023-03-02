package controller;

import java.util.List;
import java.util.Map;

import domain.Deck;
import domain.Participant;
import domain.Participants;
import domain.Player;
import domain.Result;
import service.GameService;
import service.SettingService;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private static final int INITIAL_CARD_COUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private final SettingService settingService;

    public BlackJackController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.settingService = new SettingService();
    }

    public void run() {
        Deck deck = settingService.createDeck();
        Participants participants = settingService.createParticipants(inputView.requestNames());
        GameService gameService = new GameService(deck, participants);

        initGameStatus(gameService);
        play(participants, gameService);
        showResults(gameService);
    }

    private void showResults(GameService gameService) {
        printScores(gameService);
        printResults(gameService);
    }

    private void play(Participants participants, GameService gameService) {
        hitOrStayForEachPlayer(participants, gameService);
        hitOrStayForDealer(gameService);
    }

    private void initGameStatus(GameService gameService) {
        init(gameService);
        printInitHands(gameService);
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

    private void printScores(GameService gameService) {
        for (Map.Entry<Participant, String> participantScore : gameService.getParticipantScores().entrySet()) {
            outputView.printParticipantHandValue(participantScore.getKey().getName(),
                participantScore.getKey().getCardNames(), participantScore.getValue());
        }
    }

    private void hitOrStayForDealer(GameService gameService) {
        while (gameService.isDealerHandValueUnderStandard()) {
            gameService.dealerHit();
            outputView.printDealerPickCardMessage();
        }
    }

    private void hitOrStayForEachPlayer(Participants participants, GameService gameService) {
        for (Player player : participants.findPlayers()) {
            hitOrStay(gameService, player);
        }
    }

    private void printInitHands(GameService gameService) {
        for (Map.Entry<String, List<String>> participantHand : gameService.getParticipantsInitHands().entrySet()) {
            outputView.printParticipantCard(participantHand.getKey(), participantHand.getValue());
        }
        outputView.printEmptyLine();
    }

    private void init(GameService gameService) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            gameService.dealCardsToParticipants();
        }

        outputView.printInitializingFinishMessage(gameService.getParticipantsNames());
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
}
