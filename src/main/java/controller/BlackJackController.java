package controller;

import domain.card.Deck;
import domain.card.RandomShuffleStrategy;
import domain.participant.*;
import domain.result.GameResult;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackController {

    private static final int INITIAL_CARD_COUNT = 2;
    private static final String HIT_RESPONSE = "y";

    private final Deck deck;
    public BlackJackController() {
        deck = Deck.from(new RandomShuffleStrategy());
    }

    public void run() {
        Names names = makeNames();
        Participants participants = makeParticipants(names);

        dealInitialCards(deck, participants);
        OutputView.printPlayersName(participants.getPlayersName());
        showParticipantsInitCardsStatus(participants);

        play(deck, participants);
        final Map<Participant, Integer> participantIntegerMap = participants.makePlayerFinalHandValue();
        final GameResult gameResult = new GameResult(participantIntegerMap, participants.findDealer());
        showResults(gameResult);
    }

    private Names makeNames() {
        try {
            List<Name> names = InputView.requestNames();
            return new Names(names);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return makeNames();
        }
    }
    private Participants makeParticipants(Names names) {
        try {
            List<Player> players = names.getNames().stream()
                    .map(name -> Player.create(name, InputView.requestBetAmount(name)))
                    .collect(Collectors.toList());
            return Participants.from(players);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return makeParticipants(names);
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
            OutputView.printParticipantCard(participantHand.getKey(), participantHand.getValue());
        }
        OutputView.printEmptyLine();
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
            OutputView.printExceptionMessage(e);
            playerHitOrStay(player, deck);
        }
    }

    private void drawCardByInput(Participant player, Deck deck) {
        while (isHit(InputView.requestDrawingCard(player.getName()))) {
            player.receiveCard(deck.draw());
            OutputView.printParticipantCard(player.getName(), player.getCardNames());
        }
    }

    private boolean isHit(String drawingInput) {
        return drawingInput.equals(HIT_RESPONSE);
    }

    private void dealerContinueDrawingCards(final Deck deck, final Participants participants) {
        while (participants.shouldDealerHit()) {
            participants.findDealer().receiveCard(deck.draw());
            OutputView.printDealerPickCardMessage();
        }
    }

    private void showResults(GameResult gameResult) {
        showParticipantsScore(gameResult);
        OutputView.printResultInfo();
        showParticipantsWinningStatus(gameResult);
    }

    private void showParticipantsScore(final GameResult gameResult) {
        for (Map.Entry<Participant, Boolean> participantScore : gameResult.getParticipantsBustStatus().entrySet()) {
            OutputView.printParticipantHandValue(
                    participantScore.getKey().getName(),
                    participantScore.getKey().getHandValue(),
                    participantScore.getKey().getCards(),
                    participantScore.getValue());
        }
    }

    private void showParticipantsWinningStatus(final GameResult gameResult) {
        int dealerPrize = 0;

        Map<Participant, Integer> playersPrize = gameResult.calculatePlayersPrize();
        for (Map.Entry<Participant, Integer> playerPrize : playersPrize.entrySet()) {
            dealerPrize += playerPrize.getValue() * -1;
        }

        OutputView.printParticipantsPrize(dealerPrize, playersPrize);
    }
}
