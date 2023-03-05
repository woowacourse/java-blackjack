package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

public class BlackJackController {

    private static final int INITIAL_CARD_COUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private final GameResultManager gameResultManager;

    public BlackJackController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.gameResultManager = new GameResultManager();
    }

    public void run() {
        Deck deck = Deck.from(new RandomShuffleStrategy());
        Participants participants = makeParticipants();

        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            participants.deal(deck);
        }

        outputView.printInitializingFinishMessage(participants.getPlayersName());

        final Map<String, List<String>> participantsCard = gameResultManager.getParticipantsCard(participants);

        for (Map.Entry<String, List<String>> participantHand : participantsCard.entrySet()) {
            outputView.printParticipantCard(participantHand.getKey(), participantHand.getValue());
        }
        outputView.printEmptyLine();

        //play
        for (Participant player : participants.findPlayers()) {
            keepHitOrStay(player, deck);
        }

        while (participants.shouldDealerHit()) {
            participants.findDealer().receiveCard(deck.draw());
            outputView.printDealerPickCardMessage();
        }

        //showResults
        for (Map.Entry<Participant, Boolean> participantScore : participants.getIsBust().entrySet()) {
            outputView.printParticipantHandValue(participantScore.getKey().getName(),
                    participantScore.getKey().getCards(), participantScore.getValue());
        }

        outputView.printResultInfo();

        Map<String, Result> playerResults = participants.getPlayerResults();
        Map<Result, Integer> dealerResults = participants.getDealerResults(playerResults);
        outputView.printDealerResult(dealerResults);
        for (Map.Entry<String, Result> playerResult : playerResults.entrySet()) {
            outputView.printPlayerResult(playerResult.getKey(), playerResult.getValue());
        }
    }

    private Participants makeParticipants() {
        try {
            return Participants.from(inputView.requestNames());
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return makeParticipants();
        }
    }
    private void keepHitOrStay(Participant player, Deck deck) {
        try {
            hitOrStay(player, deck);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            keepHitOrStay(player, deck);
        }
    }

    private void hitOrStay(Participant player, Deck deck) {
        while (isHit(inputView.requestDrawingCard(player.getName()))) {
            player.receiveCard(deck.draw());
            outputView.printParticipantCard(player.getName(), player.getCardNames());
        }
    }

    private boolean isHit(String drawingInput) {
        return drawingInput.equals("y");
    }
}
