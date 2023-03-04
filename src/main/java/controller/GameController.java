package controller;

import domain.card.Card;
import domain.card.Deck;
import domain.card.RandomUniqueCardSelector;
import domain.game.GameManager;
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

import static view.message.Message.DEALER_DRAW_MESSAGE;

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
        final GameManager gameManager = GameManager.create(deck, participants);
        handCards(participants, gameManager);
        printParticipantCards(participants);
        drawPlayersCard(participants, gameManager);
        handleDealerCards(participants, gameManager);
        printGameResult(participants);
        printFinalGameResult(participants);
    }

    private void printGameResult(final Participants participants) {
        OutputView.print(System.lineSeparator().trim());

        final Participant dealer = participants.findDealer();
        final List<Participant> players = participants.findPlayers();

        printParticipantCardResult(dealer);
        players.forEach(this::printParticipantCardResult);
    }

    private void printParticipantCardResult(final Participant participant) {
        final List<Card> participantCards = participant.getCard();
        final int participantScore = participant.calculateScore();

        outputView.printCardResult(participant.getName(), participantCards, participantScore);
    }

    private Participants makeParticipants() {
        return inputView.getInputWithRetry(() -> {
            final List<String> participantNames = inputView.getParticipantNames();

            return Participants.create(participantNames);
        });
    }

    private void handCards(final Participants participants, final GameManager gameManager) {
        final int size = participants.size();

        IntStream.range(0, size)
                .forEach(participantOrder -> gameManager.giveCards(participantOrder, START_GIVEN_COUNT));
    }

    private void printParticipantCards(final Participants participants) {
        outputView.printParticipantMessage(participants);
        printAllParticipantCards(participants);
    }

    private void printAllParticipantCards(final Participants participants) {
        final List<Participant> allParticipants = participants.getParticipants();

        for (Participant participant : allParticipants) {
            final List<Card> participantCards = participant.getStartCard();
            final String participantName = participant.getName();

            outputView.printParticipantCards(participantName, participantCards);
        }
    }

    private void drawPlayersCard(final Participants participants, final GameManager gameManager) {
        final List<Participant> players = participants.findPlayers();

        for (int playerIndex = 0; playerIndex < players.size(); playerIndex++) {
            final Participant player = players.get(playerIndex);
            handleDrawCard(gameManager, playerIndex, player);
        }
    }

    private void handleDrawCard(final GameManager gameManager, final int playerIndex, final Participant player) {
        DrawCardCommand drawCardCommand = DrawCardCommand.CARD_DRAW_AGAIN;

        while (canDrawCard(player, drawCardCommand)) {
            drawCardCommand = inputDrawCardCommand(player);
            processDrawCard(gameManager, playerIndex, drawCardCommand);
            outputView.printParticipantCards(player.getName(), player.getCard());
        }
    }

    private void processDrawCard(final GameManager gameManager, final int playerIndex,
            final DrawCardCommand drawCardCommand) {
        if (drawCardCommand.isDrawAgain()) {
            gameManager.giveCards(playerIndex + PLAYER_ORDER_OFFSET, PARTICIPANT_GIVEN_COUNT);
        }
    }

    private boolean canDrawCard(final Participant player, final DrawCardCommand drawCardCommand) {
        return player.canDraw() && drawCardCommand.isDrawAgain();
    }

    private DrawCardCommand inputDrawCardCommand(final Participant player) {
        return inputView.getInputWithRetry(() -> {
            final String command = inputView.getDrawCardCommand(player.getName());

            return DrawCardCommand.findCardCommand(command);
        });
    }

    private void handleDealerCards(final Participants participants, final GameManager gameManager) {
        final Dealer dealer = (Dealer) participants.findDealer();

        OutputView.print(System.lineSeparator().trim());
        while (dealer.canDraw()) {
            gameManager.giveCards(DEALER_ORDER, PARTICIPANT_GIVEN_COUNT);
            OutputView.print(String.format(DEALER_DRAW_MESSAGE.getMessage(), dealer.getName()));
        }
    }

    private void printFinalGameResult(final Participants participants) {
        final Dealer dealer = (Dealer) participants.findDealer();
        final Map<String, Result> playerGameResults = makeFinalGameResult(participants, dealer);

        outputView.printFinalGameResult(dealer.getName(), playerGameResults);
    }

    private Map<String, Result> makeFinalGameResult(final Participants participants, final Dealer dealer) {
        return participants.findPlayers().stream()
                .collect(Collectors.toMap(Participant::getName, dealer::calculateResult,
                (newValue, oldValue) -> oldValue, LinkedHashMap::new));
    }
}
