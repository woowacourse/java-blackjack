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

        dealInitialCards(deck, participants);
        outputView.printPlayersName(participants.getPlayersName());
        showParticipantsInitCardsStatus(participants);

        play(deck, participants);
        final Map<Participant, Integer> participantIntegerMap = participants.makeParticipantFinalHandValue();
        final GameResultManager gameResultManager = new GameResultManager(participantIntegerMap);
        showResults(participants, gameResultManager);
    }

    private Participants makeParticipants() {
        try {
            return Participants.from(inputView.requestNames());
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return makeParticipants();
        }
    }

    private static void dealInitialCards(final Deck deck, final Participants participants) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            participants.deal(deck);
        }
    }

    private void showParticipantsInitCardsStatus(final Participants participants) {
        final Participant dealer = participants.findDealer();
        Map<String, List<String>> participantsHands = new LinkedHashMap<>();

        participantsHands.put(dealer.getName(), dealer.getCardNames().subList(0, 1));
        insertPlayersCardsStatus(participants, participantsHands);

        for (Map.Entry<String, List<String>> participantHand : participantsHands.entrySet()) {
            outputView.printParticipantCard(participantHand.getKey(), participantHand.getValue());
        }
        outputView.printEmptyLine();
    }

    private static void insertPlayersCardsStatus(final Participants participants, final Map<String, List<String>> participantsHands) {
        for (Participant participant : participants.findPlayers()) {
            participantsHands.put(participant.getName(), participant.getCardNames());
        }
    }

    private void play(final Deck deck, final Participants participants) {
        playersDrawCard(deck, participants);
        dealerContinueDrawingCards(deck, participants);
    }

    private void playersDrawCard(final Deck deck, final Participants participants) {
        for (Participant player : participants.findPlayers()) {
            playerHitOrStay(player, deck);
        }
    }

    private void playerHitOrStay(Participant player, Deck deck) {
        try {
            drawCardByInput(player, deck);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            playerHitOrStay(player, deck);
        }
    }

    private void drawCardByInput(Participant player, Deck deck) {
        while (isHit(inputView.requestDrawingCard(player.getName()))) {
            player.receiveCard(deck.draw());
            outputView.printParticipantCard(player.getName(), player.getCardNames());
        }
    }

    private boolean isHit(String drawingInput) {
        return drawingInput.equals("y");
    }

    private void dealerContinueDrawingCards(final Deck deck, final Participants participants) {
        while (participants.shouldDealerHit()) {
            participants.findDealer().receiveCard(deck.draw());
            outputView.printDealerPickCardMessage();
        }
    }

    private void showResults(final Participants participants, GameResultManager gameResultManager) {
        showParticipantsScore(gameResultManager);
        outputView.printResultInfo();
        showParticipantsWinningStatus(participants);
    }

    private void showParticipantsScore(final GameResultManager gameResultManager) {
        for (Map.Entry<Participant, Boolean> participantScore : gameResultManager.getParticipantsBustStatus().entrySet()) {
            outputView.printParticipantHandValue(participantScore.getKey().getName(),
                    participantScore.getKey().getCards(), participantScore.getValue());
        }
    }

    private void showParticipantsWinningStatus(final Participants participants) {
        Map<String, Result> playerResults = participants.getPlayerStatus();
        Map<Result, Integer> dealerResults = participants.getDealerStatus(playerResults);
        outputView.printDealerResult(dealerResults);
        for (Map.Entry<String, Result> playerResult : playerResults.entrySet()) {
            outputView.printPlayerResult(playerResult.getKey(), playerResult.getValue());
        }
    }
}
