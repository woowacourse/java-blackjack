package controller;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Hand;
import domain.participant.ParticipantGroup;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.participant.Players;
import domain.result.GameState;
import domain.result.Result;
import domain.result.ResultJudge;
import service.BlackJackService;
import util.Parser;
import util.Validator;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackController {
    private static final String HIT_COMMAND = "y";

    private final ResultJudge resultJudge;
    private final BlackJackService blackJackService;

    public BlackJackController(ResultJudge resultJudge, BlackJackService blackJackService) {
        this.resultJudge = resultJudge;
        this.blackJackService = blackJackService;
    }

    public void run() {
        GameState gameState = prepareGame();
        playPlayers(gameState);
        playDealer(gameState);
        showResult(gameState);
    }

    private GameState prepareGame() {
        CardDeck cardDeck = new CardDeck();
        Players players = getPlayer();
        Dealer dealer = new Dealer(new Hand());

        initGame(cardDeck, new ParticipantGroup(players, dealer));

        return new GameState(new ParticipantGroup(players, dealer), cardDeck);
    }

    private void playPlayers(GameState gameState) {
        for (Player player : gameState.getPlayers().getAllPlayers()) {
            progressGame(gameState.getCardDeck(), player);
        }
    }

    private void playDealer(GameState gameState) {
        int hitCount = blackJackService.dealerHitOrStand(gameState.getDealer(), gameState.getCardDeck());

        for (int i = 0; i < hitCount; i++) {
            OutputView.dealerHitMessage();
        }
    }

    private void showResult(GameState gameState) {
        OutputView.scoreStatisticsMessage(gameState.getDealer(), gameState.getPlayers());
        Result result = resultJudge.calculateResult(gameState.getDealer(), gameState.getPlayers());
        OutputView.gameResultMessage(result);
    }

    private Players getPlayer() {
        List<Player> players = new ArrayList<>();

        OutputView.inputPlayerMessage();
        String input = InputView.input();
        Validator.validateInputName(input);
        List<String> names = Parser.separateBySeparator(input);

        for (String name : names) {
            players.add(new Player(new PlayerName(name), new Hand()));
        }
        return new Players(players);
    }

    private void initGame(CardDeck cardDeck, ParticipantGroup participantGroup) {
        blackJackService.dealInitialCards(cardDeck, participantGroup);
        OutputView.gameStartMessage(participantGroup.getDealer(), participantGroup.getPlayers());
    }

    private boolean isHitRequested(Player player) {
        OutputView.hitOrStandMessage(player);
        String input = InputView.input();
        Validator.validateChoiceInput(input);
        return HIT_COMMAND.equals(input);
    }

    private boolean canPlayerDraw(Player player) {
        if (player.isBust()) {
            player.getTotalCardScore();
            return false;
        }
        return isHitRequested(player);
    }

    private void drawAndShowCard(CardDeck cardDeck, Player player) {
        player.keepCard(cardDeck.drawCard());
        OutputView.holdingCardMessage(player);
    }

    private void finalizePlayerTurn(Player player) {
        OutputView.holdingCardMessage(player);
    }

    private void progressGame(CardDeck cardDeck, Player player) {
        while (canPlayerDraw(player)) {
            drawAndShowCard(cardDeck, player);
        }
        if (!player.isBust()) {
            finalizePlayerTurn(player);
        }
    }
}
