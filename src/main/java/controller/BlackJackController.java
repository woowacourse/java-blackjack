package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
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

        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            participants.deal(deck);
        }

//        final List<String> playerNames = participants.findPlayers().stream()
//                .map(Participant::getName)
//                .collect(Collectors.toList());
        outputView.printInitializingFinishMessage(participants.getPlayersName());

        Map<String, List<String>> participantsHands = new LinkedHashMap<>();
        participantsHands.put(participants.findDealer().getName(), participants.findDealer().getCardNames());
        for (Participant participant : participants.findPlayers()) {
            participantsHands.put(participant.getName(), participant.getCardNames());
        }

        participantsHands.put(participants.findDealer().getName(), participants.findDealer().getCardNames());
        participantsHands.replace("딜러", participantsHands.get("딜러").subList(0, 1));
        for (Participant participant : participants.findPlayers()) {
            participantsHands.put(participant.getName(), participant.getCardNames());
        }

        for (Map.Entry<String, List<String>> participantHand : participantsHands.entrySet()) {
            outputView.printParticipantCard(participantHand.getKey(), participantHand.getValue());
        }
        outputView.printEmptyLine();

        //play
        for (Participant player : participants.findPlayers()) {
            keepHitOrStay(player, deck);
        }

        while (participants.findDealer().getHandValue() < 17) {
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
