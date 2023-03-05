package controller;

import java.util.List;
import java.util.Map;

import domain.card.Deck;
import domain.people.Participant;
import domain.people.Participants;
import domain.people.Player;
import domain.game.Result;
import service.GameService;
import service.SettingService;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private static final int INITIAL_CARD_COUNT = 2;

    private final SettingService settingService;

    public BlackJackController() {
        this.settingService = new SettingService();
    }

    public void run() {
        Deck deck = settingService.createDeck();
        Participants participants = makeParticipants();
        GameService gameService = new GameService(deck, participants);

        initGameStatus(gameService);
        play(participants, gameService);
        showResults(gameService);
    }

    private Participants makeParticipants() {
        try {
            List<String> names = InputView.requestNames();
            return settingService.createParticipants(names);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
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
        List<String> participantsNames = gameService.getParticipantsNames();
        OutputView.printInitializingFinishMessage(participantsNames);
    }

    private void printInitHands(GameService gameService) {
        for (Map.Entry<String, List<String>> participantHand : gameService.getParticipantsInitHands().entrySet()) {
            OutputView.printParticipantCard(participantHand.getKey(), participantHand.getValue());
        }
        OutputView.printEmptyLine();
    }

    private void play(Participants participants, GameService gameService) {
        hitOrStayForEachPlayer(participants, gameService);
        hitOrStayForDealer(gameService);
    }

    private void hitOrStayForEachPlayer(Participants participants, GameService gameService) {
        List<Player> players = participants.findPlayers();
        for (Player player : players) {
            keepHitOrStay(gameService, player);
        }
    }

    private void keepHitOrStay(GameService gameService, Player player) {
        try {
            hitOrStay(gameService, player);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            keepHitOrStay(gameService, player);
        }
    }

    private void hitOrStay(GameService gameService, Player player) {
        String hitRequest = InputView.requestDrawingCard(player.getName());
        while (gameService.isHit(hitRequest)) {
            gameService.hit(player);
            printIfPoolDoesNotContainsHand(player, gameService);
        }
        printIfPoolDoesNotContainsHand(player, gameService);
    }

    private void printIfPoolDoesNotContainsHand(Player player, GameService gameService) {
        List<String> cardNames = player.getCardNames();
        if (!gameService.existHandInPool(cardNames)) {
            gameService.addHandToPool(cardNames);
            OutputView.printParticipantCard(player.getName(), cardNames);
        }
    }

    private void hitOrStayForDealer(GameService gameService) {
        while (gameService.isDealerHandValueUnderStandard()) {
            gameService.dealerHit();
            OutputView.printDealerPickCardMessage();
        }
    }

    private void showResults(GameService gameService) {
        printScores(gameService);
        printResults(gameService);
    }

    private void printScores(GameService gameService) {
        for (Map.Entry<Participant, String> participantScore : gameService.getParticipantScores().entrySet()) {
            OutputView.printParticipantHandValue(participantScore.getKey().getName(),
                participantScore.getKey().getCardNames(), participantScore.getValue());
        }
    }

    private void printResults(GameService gameService) {
        OutputView.printResultInfo();

        Map<String, Result> playerResults = gameService.calculatePlayerResults();
        Map<Result, Integer> dealerResults = gameService.calculateDealerResults(playerResults);
        OutputView.printDealerResult(dealerResults);
        for (Map.Entry<String, Result> playerResult : playerResults.entrySet()) {
            OutputView.printPlayerResult(playerResult.getKey(), playerResult.getValue());
        }
    }
}
