package controller;

import domain.bet.BetAmount;
import domain.bet.BetTable;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Hand;
import domain.participant.ParticipantGroup;
import domain.participant.Player;
import domain.participant.PlayerName;
import domain.participant.Players;
import domain.result.GameState;
import domain.result.ProfitCalculator;
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
        Players players = getPlayer();
        BetTable betTable = playBet(players);
        GameState gameState = prepareGame(players);

        playPlayers(gameState);
        playDealer(gameState);
        showResult(gameState, betTable);
    }

    private BetTable playBet(Players players) {
        BetTable betTable = new BetTable();
        for (Player player : players.getAllPlayers()) {
            BetAmount betAmount = getBetAmount(player);
            betTable.placeBet(player.getName(), betAmount.getAmount());
        }
        return betTable;
    }

    private BetAmount getBetAmount(Player player) {
        OutputView.betMessage(player);
        String input = InputView.input();
        Validator.validateInputIsNotNullOrBlank(input);
        int betAmount = Parser.inputToNumber(input);
        return new BetAmount(betAmount);
    }

    private GameState prepareGame(Players players) {
        CardDeck cardDeck = new CardDeck();
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

    private void showResult(GameState gameState, BetTable betTable) {
        OutputView.scoreStatisticsMessage(gameState.getDealer(), gameState.getPlayers());
        Result result = resultJudge.calculateResult(gameState.getDealer(), gameState.getPlayers());
        ProfitCalculator calculator = new ProfitCalculator(betTable);
        for (Player player : gameState.getPlayers().getAllPlayers()) {
            calculator.playerCalculateProfit(result.getGameResult().get(player.getName()), player);
        }
        int dealerProfit = calculator.dealerCalculateProfit();
        OutputView.gameProfitResultMessage(betTable, dealerProfit);
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
