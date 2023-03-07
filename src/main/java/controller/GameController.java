package controller;

import domain.CardShuffler;
import domain.card.Card;
import domain.card.CardRandomShuffler;
import domain.card.Deck;
import domain.game.GameManager;
import domain.game.GameResult;
import domain.game.Result;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static view.message.Message.BLACKJACK_MESSAGE;
import static view.message.Message.BUST_MESSAGE;
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
        final Deck deck = makeDeck();
        final GameManager gameManager = GameManager.create(deck, participants);
        handCards(gameManager);
        printParticipantCards(participants);
        drawPlayersCard(participants, gameManager);
        handleDealerCards(participants, gameManager);
        printGameResult(participants);
        printFinalGameResult(participants);
    }

    private Participants makeParticipants() {
        return inputView.getInputWithRetry(() -> {
            List<String> participantNames = inputView.getPlayerNames();
            return Participants.create(participantNames);
        });
    }

    private static Deck makeDeck() {
        final CardShuffler cardShuffler = new CardRandomShuffler();
        return Deck.create(cardShuffler);
    }

    private void handCards(final GameManager gameManager) {
        final int participantSize = gameManager.getParticipantSize();
        IntStream.range(0, participantSize)
                .forEach(participantOrder -> gameManager.giveCards(participantOrder, START_GIVEN_COUNT));
    }

    private void printParticipantCards(final Participants participants) {
        outputView.printParticipantMessage(participants);
        printDealerCard(participants);
        printPlayerCards(participants);
    }

    private void printDealerCard(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        final Card dealerFirstCard = dealer.getFirstCard();
        outputView.printDealerCard(dealer.getName(), dealerFirstCard);
    }

    private void printPlayerCards(final Participants participants) {
        final List<Participant> players = participants.getPlayer();
        for (Participant player : players) {
            List<Card> playerCards = player.getCard();
            outputView.printPlayerCard(player.getName(), playerCards);
        }
    }

    private void drawPlayersCard(final Participants participants, final GameManager gameManager) {
        final List<Participant> players = participants.getPlayer();
        for (int playerIndex = 0; playerIndex < players.size(); playerIndex++) {
            final Participant player = players.get(playerIndex);
            handleDrawCard(gameManager, playerIndex, player);
        }
    }

    private void handleDrawCard(final GameManager gameManager,
                                final int playerIndex,
                                final Participant player) {
        DrawCardCommand drawCardCommand = getDrawCardCommand(player);
        checkDraw(gameManager, playerIndex, drawCardCommand);
        outputView.printPlayerCard(player.getName(), player.getCard());
        if (cannotDrawCard(player, drawCardCommand)) {
            return;
        }
        handleDrawCard(gameManager, playerIndex, player);
    }

    private DrawCardCommand getDrawCardCommand(final Participant player) {
        return inputView.getInputWithRetry(() -> {
            final String command = inputView.getDrawCardCommand(player.getName());
            return DrawCardCommand.findCardCommand(command);
        });
    }

    private void checkDraw(final GameManager gameManager,
                           final int playerIndex,
                           final DrawCardCommand drawCardCommand) {
        if (drawCardCommand.isDrawStop()) {
            return;
        }
        gameManager.giveCards(playerIndex + PLAYER_ORDER_OFFSET, PARTICIPANT_GIVEN_COUNT);
    }

    private boolean cannotDrawCard(final Participant player, final DrawCardCommand drawCardCommand) {
        return isBust(player) || isBlackJack(player) || drawCardCommand.isDrawStop();
    }

    private boolean isBust(final Participant player) {
        final boolean isBust = player.isBust();
        if (isBust) {
            OutputView.print(BUST_MESSAGE.getMessage());
        }
        return isBust;
    }

    private boolean isBlackJack(final Participant player) {
        final boolean isBlackJack = player.isBlackJack();
        if (isBlackJack) {
            OutputView.print(BLACKJACK_MESSAGE.getMessage());
        }
        return isBlackJack;
    }

    private void handleDealerCards(final Participants participants, final GameManager gameManager) {
        final Dealer dealer = participants.getDealer();
        OutputView.print(System.lineSeparator().trim());
        while (dealer.canGiveCard()) {
            gameManager.giveCards(DEALER_ORDER, PARTICIPANT_GIVEN_COUNT);
            OutputView.print(String.format(DEALER_DRAW_MESSAGE.getMessage(),
                    dealer.getName()) + System.lineSeparator());
        }
    }

    private void printGameResult(final Participants participants) {
        final Participant dealer = participants.getDealer();
        printParticipantCardResult(dealer);
        final List<Participant> players = participants.getPlayer();
        players.forEach(this::printParticipantCardResult);
    }

    private void printParticipantCardResult(final Participant participant) {
        final List<Card> participantCards = participant.getCard();
        final int participantScore = participant.calculateScore();
        outputView.printCardResult(participant.getName(), participantCards, participantScore);
    }

    private void printFinalGameResult(final Participants participants) {
        final GameResult gameResult = GameResult.create(participants);
        final Map<String, Result> playerGameResults = gameResult.getPlayerGameResults();
        final Participant dealer = participants.getDealer();
        outputView.printFinalGameResult(dealer.getName(), playerGameResults);
    }
}
