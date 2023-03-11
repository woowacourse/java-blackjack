package controller;

import domain.card.CardRandomShuffler;
import domain.game.GameManager;
import domain.participant.Participant;
import domain.participant.ParticipantInfo;
import domain.participant.ParticipantMoney;
import domain.participant.Players;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        final Map<Participant, ParticipantMoney> initParticipantInfo = getParticipantInfo();
        final GameManager gameManager = makeGameManager(initParticipantInfo);
        final ParticipantInfo participantInfo = gameManager.getParticipantInfo();
        printFirstTurnResult(gameManager, participantInfo);
        gameManager.judgeFirstBettingResult();
        drawPlayersCard(gameManager, participantInfo);
        handleDealerCards(gameManager, participantInfo);
        printCardResult(participantInfo);
        printFinalGameResult(gameManager, initParticipantInfo);
    }

    private Map<Participant, ParticipantMoney> getParticipantInfo() {
        final Map<Participant, ParticipantMoney> participantInfo = new LinkedHashMap<>();
        participantInfo.put(Participant.createDealer(), ParticipantMoney.zero());
        for (Participant player : makePlayers()) {
            participantInfo.put(player, getPlayerBettingMoneys(player));
        }
        return participantInfo;
    }

    private List<Participant> makePlayers() {
        return inputView.getInputWithRetry(() -> {
            final List<String> playerNames = inputView.getPlayerNames();
            final Players players = Players.create(playerNames);
            return players.getPlayers();
        });
    }

    private ParticipantMoney getPlayerBettingMoneys(final Participant player) {
        return inputView.getInputWithRetry(() -> {
            String bettingMoney = inputView.getBettingMoney(player.getName());
            return ParticipantMoney.create(bettingMoney);
        });
    }

    private GameManager makeGameManager(final Map<Participant, ParticipantMoney> participantInfo) {
        final CardRandomShuffler cardRandomShuffler = new CardRandomShuffler();
        return GameManager.create(cardRandomShuffler, participantInfo);
    }

    private void printFirstTurnResult(final GameManager gameManager, final ParticipantInfo participantInfo) {
        gameManager.handFirstCards();
        outputView.printHandMessage(participantInfo.findDealerInfo(), participantInfo.findPlayerInfo());
    }

    private void drawPlayersCard(final GameManager gameManager, final ParticipantInfo participantInfo) {
        participantInfo.findPlayerInfo()
                .forEach(player -> handleDrawCard(gameManager, player));
    }

    private void handleDrawCard(final GameManager gameManager, final Participant player) {
        DrawCardCommand drawCardCommand = getDrawCardCommand(player);
        checkDraw(gameManager, player, drawCardCommand);
        checkBust(player, gameManager);
        outputView.printParticipantCard(player.getName(), player.getHand());
        if (isPlayerEnd(player, drawCardCommand)) {
            return;
        }
        handleDrawCard(gameManager, player);
    }

    private void checkBust(final Participant player, final GameManager gameManager) {
        if (player.isBust()) {
            gameManager.losePlayerMoney(player);
        }
    }

    private boolean isPlayerEnd(final Participant player, final DrawCardCommand drawCardCommand) {
        if (cannotDrawCard(player, drawCardCommand)) {
            printMessageIfPlayerBust(player);
            printMessageIfPlayerBlackJack(player);
            return true;
        }
        return false;
    }

    private void printMessageIfPlayerBust(final Participant player) {
        if (player.isBust()) {
            outputView.printBustMessage();
        }
    }

    private void printMessageIfPlayerBlackJack(final Participant player) {
        if (player.isBlackJack()) {
            outputView.printBlackJackMessage();
        }
    }

    private DrawCardCommand getDrawCardCommand(final Participant player) {
        return inputView.getInputWithRetry(() -> {
            final String command = inputView.getDrawCardCommand(player.getName());
            return DrawCardCommand.findCardCommand(command);
        });
    }

    private void checkDraw(final GameManager gameManager, final Participant player,
                           final DrawCardCommand drawCardCommand) {
        if (cannotDrawCard(player, drawCardCommand)) {
            return;
        }
        gameManager.handCard(player);
    }

    private boolean cannotDrawCard(final Participant player, final DrawCardCommand drawCardCommand) {
        return player.isBust() || player.isBlackJack() || drawCardCommand.isDrawStop();
    }

    private void handleDealerCards(final GameManager gameManager, final ParticipantInfo participantInfo) {
        OutputView.print(System.lineSeparator().trim());
        final Participant dealer = participantInfo.findDealerInfo();
        while (dealer.canGiveCard()) {
            gameManager.handCard(dealer);
            outputView.printDealerDrawMessage(dealer.getName());
        }
    }

    private void printCardResult(final ParticipantInfo participantInfo) {
        final Participant dealer = participantInfo.findDealerInfo();
        final List<Participant> players = participantInfo.findPlayerInfo();
        printParticipantCardResult(dealer);
        players.forEach(this::printParticipantCardResult);
    }

    private void printParticipantCardResult(final Participant participant) {
        outputView.printCardResult(participant.getName(), participant.getHand(), participant.calculateScore());
    }

    private void printFinalGameResult(final GameManager gameManager,
                                      final Map<Participant, ParticipantMoney> initMoneyInfo) {
        gameManager.calculateProfit(initMoneyInfo);
        outputView.printFinalGameResult(gameManager.getPlayerGameResults());
    }
}
