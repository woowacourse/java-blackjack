package controller;

import domain.game.GameManager;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import view.InputView;
import view.OutputView;

import java.util.List;

import static view.message.Message.BLACKJACK_MESSAGE;
import static view.message.Message.BUST_MESSAGE;
import static view.message.Message.DEALER_DRAW_MESSAGE;

public class GameProcessController {

    private static final int PARTICIPANT_GIVEN_COUNT = 1;
    private static final int PLAYER_ORDER_OFFSET = 1;
    private static final int DEALER_ORDER = 0;

    private final InputView inputView;
    private final OutputView outputView;

    private GameProcessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public static GameProcessController create(final InputView inputView, final OutputView outputView) {
        return new GameProcessController(inputView, outputView);
    }

    public void start(final Participants participants, final GameManager gameManager) {
        drawPlayersCard(participants, gameManager);
        handleDealerCards(participants, gameManager);
        displayGameResult(participants);
    }

    private void drawPlayersCard(final Participants participants, final GameManager gameManager) {
        List<Participant> players = participants.getPlayer();
        for (int playerIndex = 0; playerIndex < players.size(); playerIndex++) {
            Participant player = players.get(playerIndex);
            handleDrawCard(gameManager, playerIndex, player);
        }
    }

    private void handleDrawCard(final GameManager gameManager, final int playerIndex, final Participant player) {
        DrawCardCommand drawCardCommand;
        do {
            drawCardCommand = getDrawCardCommand(player);
            if (drawCardCommand.isDrawAgain()) {
                gameManager.giveCards(playerIndex + PLAYER_ORDER_OFFSET, PARTICIPANT_GIVEN_COUNT);
            }
            outputView.printPlayerCard(player.getName(), player.getCard());
        } while (canDrawCard(player, drawCardCommand));
    }

    private DrawCardCommand getDrawCardCommand(final Participant player) {
        return inputView.getInputWithRetry(() -> {
            String command = inputView.getDrawCardCommand(player.getName());
            return DrawCardCommand.findCardCommand(command);
        });
    }

    private boolean canDrawCard(final Participant player, final DrawCardCommand drawCardCommand) {
        return !isBust(player) && !isBlackJack(player) && drawCardCommand.isDrawAgain();
    }

    private boolean isBust(final Participant player) {
        boolean isBust = player.isBust();
        if (isBust) {
            OutputView.print(BUST_MESSAGE.getMessage());
        }
        return isBust;
    }

    private boolean isBlackJack(final Participant player) {
        boolean isBlackJack = player.isBlackJack();
        if (isBlackJack) {
            OutputView.print(BLACKJACK_MESSAGE.getMessage());
        }
        return isBlackJack;
    }

    private void handleDealerCards(final Participants participants, final GameManager gameManager) {
        Dealer dealer = (Dealer) participants.getDealer();
        OutputView.print(System.lineSeparator().trim());
        while (dealer.canGiveCard()) {
            gameManager.giveCards(DEALER_ORDER, PARTICIPANT_GIVEN_COUNT);
            OutputView.print(String.format(DEALER_DRAW_MESSAGE.getMessage(), dealer.getName()));
        }
    }

    private void displayGameResult(final Participants participants) {
        GameResultController gameResultController = GameResultController.create(outputView);
        gameResultController.start(participants);
    }
}
