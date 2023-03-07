package controller;

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

public final class GameController {

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

        startGame(participants, gameManager);
        playForPlayers(participants, gameManager);
        playForDealer(participants, gameManager);
        printGameResult(participants);
    }

    private Participants makeParticipants() {
        return ExceptionHandler.repeat(inputView::readParticipantNames,
                outputView::guideParticipantsName,
                Participants::create,
                outputView::printExceptionMessage);
    }

    private void startGame(final Participants participants, final GameManager gameManager) {
        gameManager.giveStartCards();
        printParticipantCards(participants);
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

    private void playForPlayers(final Participants participants, final GameManager gameManager) {
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

    private boolean canDrawCard(final Participant player, final DrawCardCommand drawCardCommand) {
        return player.canDraw() && drawCardCommand.isDrawAgain();
    }

    private void processDrawCard(final GameManager gameManager, final int playerIndex,
        final DrawCardCommand drawCardCommand) {

        if (drawCardCommand.isDrawAgain()) {
            gameManager.giveCards(playerIndex + PLAYER_ORDER_OFFSET, PARTICIPANT_GIVEN_COUNT);
        }
    }

    private void playForDealer(final Participants participants, final GameManager gameManager) {
        final Dealer dealer = (Dealer) participants.findDealer();
        final String name = dealer.getName();

        while (dealer.canDraw()) {
            gameManager.giveCards(DEALER_ORDER, PARTICIPANT_GIVEN_COUNT);
            outputView.guideDealerGivenCard(name);
        }
    }

    private void printGameResult(final Participants participants) {
        final List<Participant> totalParticipants = participants.getParticipants();

        outputView.printCardResult(totalParticipants);
        printFinalGameResult(participants);
    }

    private DrawCardCommand inputDrawCardCommand(final Participant player) {
        final String playerName = player.getName();

        return ExceptionHandler.repeat(inputView::readDrawCardCommand,
                () -> outputView.guideDrawCard(playerName),
                DrawCardCommand::findCardCommand,
                outputView::printExceptionMessage);
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
