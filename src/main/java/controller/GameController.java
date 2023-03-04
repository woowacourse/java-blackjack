package controller;

import domain.card.Deck;
import domain.card.RandomUniqueCardSelector;
import domain.card.CardManager;
import domain.participant.Result;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class GameController {

    private static final int START_GIVEN_COUNT = 2;
    private static final int PARTICIPANT_GIVEN_COUNT = 1;
    private static final int PLAYER_ORDER_OFFSET = 1;
    private static final int DEALER_ORDER = 0;

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        final Participants participants = makeParticipants();
        final Deck deck = Deck.create(new RandomUniqueCardSelector());
        final CardManager cardManager = CardManager.create(deck, participants);
        handCards(participants, cardManager);
        printParticipantCards(participants);
        drawPlayersCard(participants, cardManager);
        handleDealerCards(participants, cardManager);
        printGameResult(participants);
        printFinalGameResult(participants);
    }

    private Participants makeParticipants() {
        return ExceptionHandler.repeat(inputView::readParticipantNames,
                outputView::guideParticipantsName,
                Participants::create,
                outputView::printExceptionMessage);
    }

    private void printGameResult(final Participants participants) {
        final List<Participant> totalParticipants = participants.getParticipants();

        outputView.printCardResult(totalParticipants);
    }

    private void handCards(final Participants participants, final CardManager cardManager) {
        final int size = participants.size();

        IntStream.range(0, size)
                .forEach(participantOrder -> cardManager.giveCards(participantOrder, START_GIVEN_COUNT));
    }

    private void printParticipantCards(final Participants participants) {
        outputView.printParticipantMessage(participants);
        printAllParticipantCards(participants);
    }

    private void printAllParticipantCards(final Participants participants) {
        final Participant dealer = participants.findDealer();
        final List<Participant> players = participants.findPlayers();

        outputView.printTotalParticipantCards(dealer, players);
    }

    private void drawPlayersCard(final Participants participants, final CardManager cardManager) {
        final List<Participant> players = participants.findPlayers();

        for (int playerIndex = 0; playerIndex < players.size(); playerIndex++) {
            final Participant player = players.get(playerIndex);

            handleDrawCard(cardManager, playerIndex, player);
        }
    }

    private void handleDrawCard(final CardManager cardManager, final int playerIndex, final Participant player) {
        DrawCardCommand drawCardCommand = DrawCardCommand.CARD_DRAW_AGAIN;

        while (canDrawCard(player, drawCardCommand)) {
            drawCardCommand = inputDrawCardCommand(player);

            processDrawCard(cardManager, playerIndex, drawCardCommand);
            outputView.printParticipantCards(player.getName(), player.getCard());
        }
    }

    private boolean canDrawCard(final Participant player, final DrawCardCommand drawCardCommand) {
        return player.canDraw() && drawCardCommand.isDrawAgain();
    }

    private void processDrawCard(final CardManager cardManager, final int playerIndex,
            final DrawCardCommand drawCardCommand) {
        if (drawCardCommand.isDrawAgain()) {
            cardManager.giveCards(playerIndex + PLAYER_ORDER_OFFSET, PARTICIPANT_GIVEN_COUNT);
        }
    }

    private DrawCardCommand inputDrawCardCommand(final Participant player) {
        final String playerName = player.getName();

        return ExceptionHandler.repeat(inputView::readDrawCardCommand,
                () -> outputView.guideDrawCard(playerName),
                DrawCardCommand::findCardCommand,
                outputView::printExceptionMessage);
    }

    private void handleDealerCards(final Participants participants, final CardManager cardManager) {
        final Dealer dealer = (Dealer) participants.findDealer();
        final String name = dealer.getName();

        while (dealer.canDraw()) {
            cardManager.giveCards(DEALER_ORDER, PARTICIPANT_GIVEN_COUNT);
            outputView.guideDealerGivenCard(name);
        }
    }

    private void printFinalGameResult(final Participants participants) {
        final Dealer dealer = (Dealer) participants.findDealer();
        final Map<String, Result> playerGameResults = makeFinalGameResult(participants, dealer);
        final String dealerName = dealer.getName();

        outputView.guideFinalGameResult();
        outputView.printFinalGameResult(dealerName, playerGameResults);
    }

    private Map<String, Result> makeFinalGameResult(final Participants participants, final Dealer dealer) {
        return participants.findPlayers().stream()
                .collect(Collectors.toMap(Participant::getName, dealer::calculateResult,
                (newValue, oldValue) -> oldValue, LinkedHashMap::new));
    }
}
