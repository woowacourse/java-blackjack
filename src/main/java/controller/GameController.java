package controller;

import domain.card.Card;
import domain.card.Deck;
import domain.card.RandomUniqueCardSelector;
import domain.game.GameManager;
import domain.participant.Participants;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;
import view.message.DrawCardCommandSelector;

public final class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        final GameManager gameManager = makeGameManager();

        startGame(gameManager);
        playForPlayer(gameManager);
        playForDealer(gameManager);
        printGameResult(gameManager);
    }

    private GameManager makeGameManager() {
        final Participants participants = makeParticipants();
        final Deck deck = Deck.create(new RandomUniqueCardSelector());

        return GameManager.create(deck, participants);
    }

    private Participants makeParticipants() {
        try {
            outputView.guideParticipantsName();

            final List<String> playersName = inputView.readParticipantNames();

            return Participants.create(playersName, this::readBetAmount, outputView::printExceptionMessage);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return makeParticipants();
        }
    }

    private void startGame(final GameManager gameManager) {
        givenStartCardsForDealer(gameManager);
        givenStartCardsForPlayer(gameManager);

        printTotalParticipantStartCards(gameManager);
    }

    private void givenStartCardsForDealer(final GameManager gameManager) {
        gameManager.giveStartCardsForDealer();
    }

    private void givenStartCardsForPlayer(final GameManager gameManager) {
        final List<String> playersName = gameManager.playersName();

        for (String playerName : playersName) {
            gameManager.giveStartCardsForPlayer(playerName);
        }
    }

    private int readBetAmount(final String playerName) {
        try {
            outputView.guideBetAmount(playerName);

            return inputView.readPlayerBetAmount();
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return readBetAmount(playerName);
        }
    }

    private void printTotalParticipantStartCards(final GameManager gameManager) {
        printTotalParticipantsName(gameManager);
        printStartCard(gameManager);
    }

    private void printTotalParticipantsName(final GameManager gameManager) {
        final List<String> participantsName = gameManager.getParticipantsName();

        outputView.printGiveParticipantStartCardMessage(participantsName);
    }

    private void printStartCard(final GameManager gameManager) {
        printStartDealerCard(gameManager);
        printPlayersCard(gameManager);

        outputView.printBlank();
    }

    private void printStartDealerCard(final GameManager gameManager) {
        final String dealerName = gameManager.getDealerName();
        final Card dealerStartCard = gameManager.getDealerStartCard();
        outputView.printDealerStartCard(dealerName, dealerStartCard);
    }

    private void playForPlayer(final GameManager gameManager) {
        final List<String> playersName = gameManager.playersName();

        for (String playerName : playersName) {
            handleDrawCard(gameManager, playerName);
        }
    }

    private void handleDrawCard(final GameManager gameManager, final String playerName) {
        DrawCardCommand drawable = DrawCardCommand.CARD_DRAW_AGAIN;

        while (canDrawCard(gameManager, playerName, drawable)) {
            drawable = readDrawable(playerName);

            processPlayerDrawCard(gameManager, playerName, drawable);
            printPlayerCards(gameManager, playerName);
        }
    }

    private boolean canDrawCard(final GameManager gameManager, final String playerName, final DrawCardCommand command) {
        return gameManager.canDraw(playerName) & command.isDrawAgain();
    }

    private DrawCardCommand readDrawable(final String playerName) {
        try {
            outputView.guideDrawCard(playerName);

            final String command = inputView.readDrawCardCommand();

            return DrawCardCommandSelector.findDrawable(command);
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e.getMessage());
            return readDrawable(playerName);
        }
    }

    private void processPlayerDrawCard(final GameManager gameManager, final String playerName,
            final DrawCardCommand drawCardCommand) {
        if (drawCardCommand.isDrawAgain()) {
            gameManager.addCardForPlayer(playerName);
        }
    }

    private void printPlayerCards(final GameManager gameManager, final String targetPlayerName) {
        final List<Card> playerCards = gameManager.getPlayerCard(targetPlayerName);

        outputView.printParticipantCards(targetPlayerName, playerCards);
        outputView.printBlank();
    }

    private void playForDealer(final GameManager gameManager) {
        final String dealerName = gameManager.getDealerName();
        final int drawCardCount = gameManager.playDealerTurn();

        outputView.guideDealerGivenCard(dealerName, drawCardCount);
    }

    private void printGameResult(final GameManager gameManager) {
        printTotalParticipantCards(gameManager);

        printProfit(gameManager);
    }

    private void printTotalParticipantCards(final GameManager gameManager) {
        final String dealerName = gameManager.getDealerName();
        final List<Card> dealerCard = gameManager.getDealerCard();
        final int dealerScore = gameManager.getDealerScore();

        outputView.printBlank();
        outputView.printCardsAndScore(dealerName, dealerCard, dealerScore);
        printPlayersCard(gameManager);
    }

    private void printPlayersCard(final GameManager gameManager) {
        final List<String> playersName = gameManager.playersName();

        for (String playerName : playersName) {
            final List<Card> playerCard = gameManager.getPlayerCard(playerName);
            final int playerScore = gameManager.getPlayerScore(playerName);

            outputView.printCardsAndScore(playerName, playerCard, playerScore);
        }
    }

    private void printProfit(final GameManager gameManager) {
        final String dealerName = gameManager.getDealerName();
        final Map<String, BigDecimal> gameResult = gameManager.getTotalPlayerGameResult();

        outputView.guideFinalGameResult();
        outputView.printFinalGameResult(dealerName, gameResult);
    }
}
