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
import domain.participant.Player;
import domain.participant.Players;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

import static view.message.Message.BLACKJACK_MESSAGE;
import static view.message.Message.BUST_MESSAGE;
import static view.message.Message.DEALER_DRAW_MESSAGE;

public final class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        final Dealer dealer = Participant.createDealer();
        final List<Player> players = getPlayers();
        final Deck deck = makeDeck();
        final GameManager gameManager = makeGameManager(dealer, players, deck);
        gameManager.handFirstCards();
        printParticipantCards(gameManager, dealer, players);
        drawPlayersCard(players, gameManager);
        handleDealerCards(dealer, gameManager);
        printGameResult(gameManager);
        printFinalGameResult(dealer, players);
    }

    private List<Player> getPlayers() {
        final Players players = makePlayers();
        return players.getPlayers();
    }

    private Players makePlayers() {
        return inputView.getInputWithRetry(() -> {
            List<String> playerNames = inputView.getPlayerNames();
            return Players.create(playerNames);
        });
    }

    private Deck makeDeck() {
        final CardShuffler cardShuffler = new CardRandomShuffler();
        return Deck.create(cardShuffler);
    }

    private GameManager makeGameManager(final Dealer dealer, final List<Player> players, final Deck deck) {
        return GameManager.create(dealer, players, deck);
    }

    private void printParticipantCards(final GameManager gameManager, final Dealer dealer, final List<Player> players) {
        outputView.printParticipantMessage(gameManager.getParticipants());
        printDealerCard(dealer);
        printPlayerCards(players);
    }

    private void printDealerCard(final Dealer dealer) {
        final Card dealerFirstCard = dealer.getFirstCard();
        outputView.printDealerCard(dealer.getName(), dealerFirstCard);
    }

    private void printPlayerCards(final List<Player> players) {
        players.forEach(player ->
                outputView.printParticipantCard(player.getName(), player.getHand()));
    }

    private void drawPlayersCard(final List<Player> players, final GameManager gameManager) {
        players.forEach(player -> handleDrawCard(gameManager, player));
    }

    private void handleDrawCard(final GameManager gameManager,
                                final Participant player) {
        DrawCardCommand drawCardCommand = getDrawCardCommand(player);
        checkDraw(gameManager, player, drawCardCommand);
        outputView.printParticipantCard(player.getName(), player.getHand());
        if (cannotDrawCard(player, drawCardCommand)) {
            return;
        }
        handleDrawCard(gameManager, player);
    }

    private DrawCardCommand getDrawCardCommand(final Participant player) {
        return inputView.getInputWithRetry(() -> {
            final String command = inputView.getDrawCardCommand(player.getName());
            return DrawCardCommand.findCardCommand(command);
        });
    }

    private void checkDraw(final GameManager gameManager,
                           final Participant player,
                           final DrawCardCommand drawCardCommand) {
        if (drawCardCommand.isDrawStop()) {
            return;
        }
        gameManager.handCard(player);
    }

    private boolean cannotDrawCard(final Participant player, final DrawCardCommand drawCardCommand) {
        return drawCardCommand.isDrawStop() || isBust(player) || isBlackJack(player);
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

    private void handleDealerCards(final Dealer dealer, final GameManager gameManager) {
        OutputView.print(System.lineSeparator().trim());
        while (dealer.canGiveCard()) {
            gameManager.handCard(dealer);
            OutputView.print(String.format(DEALER_DRAW_MESSAGE.getMessage(),
                    dealer.getName()) + System.lineSeparator());
        }
    }

    private void printGameResult(final GameManager gameManager) {
        gameManager.getParticipants()
                .forEach(this::printParticipantCardResult);
    }

    private void printParticipantCardResult(final Participant participant) {
        final List<Card> participantCards = participant.getHand();
        final int participantScore = participant.calculateScore();
        outputView.printCardResult(participant.getName(), participantCards, participantScore);
    }

    private void printFinalGameResult(final Dealer dealer, final List<Player> players) {
        final GameResult gameResult = GameResult.create(dealer, players);
        final Map<String, Result> playerGameResults = gameResult.getPlayerGameResults();
        outputView.printFinalGameResult(dealer.getName(), playerGameResults);
    }
}
