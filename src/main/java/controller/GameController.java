package controller;

import domain.card.CardRandomShuffler;
import domain.game.GameManager;
import domain.participant.Dealer;
import domain.participant.ParticipantMoney;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.Players;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public final class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        final Players players = makePlayers();
        final Map<Player, ParticipantMoney> bettingInfo = betMoney(players);
        final GameManager gameManager = makeGameManager(players);
        printFirstDealingResult(gameManager);
        gameManager.judgeFirstBettingResult();
        dealingPlayerCards(gameManager);
        dealingDealerCards(gameManager);
        printCardResult(gameManager);
        printFinalGameResult(gameManager, bettingInfo);
    }

    private Players makePlayers() {
        return inputView.getInputWithRetry(() -> {
            final List<String> playerNames = inputView.getPlayerNames();
            return Players.create(playerNames);
        });
    }

    private Map<Player, ParticipantMoney> betMoney(final Players players) {
        final Map<Player, ParticipantMoney> initPlayerMoney = new LinkedHashMap<>();
        players.getPlayers().forEach(player -> {
            final ParticipantMoney participantMoney = getPlayerBettingMoneys(player);
            player.bet(participantMoney);
            initPlayerMoney.put(player, participantMoney);
        });
        return initPlayerMoney;
    }

    private ParticipantMoney getPlayerBettingMoneys(final Player player) {
        return inputView.getInputWithRetry(() -> {
            String bettingMoney = inputView.getBettingMoney(player.getName());
            return ParticipantMoney.create(bettingMoney);
        });
    }

    private GameManager makeGameManager(final Players players) {
        final Participants participants = Participants.create(players);
        final CardRandomShuffler cardRandomShuffler = new CardRandomShuffler();
        return GameManager.create(cardRandomShuffler, participants);
    }

    private void printFirstDealingResult(final GameManager gameManager) {
        gameManager.dealingFirstTurn();
        final Participants participants = gameManager.getParticipants();
        outputView.printDealingMessage(participants.getDealer(), participants.getPlayers());
    }

    private void dealingPlayerCards(final GameManager gameManager) {
        gameManager.getParticipants()
                .getPlayers()
                .forEach(player -> handleDrawCard(gameManager, player));
    }

    private void handleDrawCard(final GameManager gameManager, final Player player) {
        final DrawCardCommand drawCardCommand = getDrawCardCommand(player);
        drawCard(gameManager, player, drawCardCommand);
        outputView.printParticipantCard(player.getName(), player.getHand());
        if (isPlayerEnd(player, drawCardCommand)) {
            return;
        }
        handleDrawCard(gameManager, player);
    }

    private DrawCardCommand getDrawCardCommand(final Player player) {
        return inputView.getInputWithRetry(() -> {
            final String command = inputView.getDrawCardCommand(player.getName());
            return DrawCardCommand.findCardCommand(command);
        });
    }

    private void drawCard(final GameManager gameManager, final Player player,
                          final DrawCardCommand drawCardCommand) {
        if (player.canDrawCard() && drawCardCommand.isDrawAgain()) {
            player.addCard(gameManager.getCard());
        }
        if (player.isBust()) {
            player.loseMoney();
        }
    }

    private boolean isPlayerEnd(final Player player, final DrawCardCommand drawCardCommand) {
        if (player.canDrawCard() && drawCardCommand.isDrawAgain()) {
            return false;
        }
        printEndMessage(player, Player::isBust, outputView::printBustMessage);
        printEndMessage(player, Player::isBlackJack, outputView::printBlackJackMessage);
        return true;
    }

    private void printEndMessage(final Player player, final Predicate<Player> isPlayerEnd, final Runnable printMessage) {
        if (isPlayerEnd.test(player)) {
            printMessage.run();
        }
    }

    private void dealingDealerCards(final GameManager gameManager) {
        OutputView.print(System.lineSeparator().trim());
        gameManager.canDealerDrawCard(outputView::printDealerDrawMessage);
    }

    private void printCardResult(final GameManager gameManager) {
        final Participants participants = gameManager.getParticipants();
        final Dealer dealer = participants.getDealer();
        outputView.printCardResult(dealer.getName(), dealer.getHand(), dealer.calculateScore());
        participants.getPlayers().forEach(player ->
                outputView.printCardResult(player.getName(), player.getHand(), player.calculateScore()));
    }

    private void printFinalGameResult(final GameManager gameManager,
                                      final Map<Player, ParticipantMoney> bettingInfo) {
        gameManager.calculateProfit(bettingInfo);
        final Participants participants = gameManager.getParticipants();
        outputView.printFinalGameResult(participants.getDealer(), participants.getPlayers());
    }
}
