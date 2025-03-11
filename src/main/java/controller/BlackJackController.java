package controller;

import controller.dto.CardScoreDto;
import domain.BlackJackGame;
import domain.GameResult;
import domain.Participant;
import domain.Player;
import domain.Score;
import domain.TrumpCard;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackJackGame blackJackGame;

    public BlackJackController(InputView inputView, OutputView outputView, BlackJackGame blackJackGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJackGame = blackJackGame;
    }

    public void run() {
        List<Player> players = executeInitializeCards();

        players.forEach(this::executePlayerHit);
        executeDealerHit();

        displayCardResult(players);
        displayGameResult(players);
    }

    private List<Player> executeInitializeCards() {
        List<Player> players = createPlayers();
        Map<String, List<TrumpCard>> playerCards = convertPlayerCards(players);
        TrumpCard dealerFirstCard = blackJackGame.retrieveDealerFirstCard();

        outputView.printInitialCards(playerCards, dealerFirstCard);

        return players;
    }

    private List<Player> createPlayers() {
        List<String> playerNames = inputView.readPlayerNames();
        return blackJackGame.createPlayers(playerNames);
    }

    private Map<String, List<TrumpCard>> convertPlayerCards(List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(Player::getName, Participant::retrieveCards));
    }

    private void executePlayerHit(Player player) {
        while (blackJackGame.isPlayerHitAllowed(player) &&
                inputView.readProcessHit(player.getName())) {
            blackJackGame.processPlayerHit(player);
            outputView.printPlayerCards(player.getName(), player.retrieveCards());
        }
    }

    private void executeDealerHit() {
        int dealerHitCount = blackJackGame.processDealerHit();
        outputView.printDealerHitInfo(dealerHitCount);
    }

    private void displayCardResult(List<Player> players) {
        Map<String, CardScoreDto> playerCardScoreDto = convertPlayerCardScoreDto(players);
        CardScoreDto dealerCardScoreDto = new CardScoreDto(
                blackJackGame.retrieveDealerCards(), blackJackGame.calculateDealerScore());
        outputView.printCardsResult(playerCardScoreDto, dealerCardScoreDto);
    }

    private Map<String, CardScoreDto> convertPlayerCardScoreDto(List<Player> players) {
        return players.stream()
                .collect(Collectors.toMap(Player::getName, player -> {
                    List<TrumpCard> playerCards = player.retrieveCards();
                    Score score = blackJackGame.calculatePlayerScore(player);

                    return new CardScoreDto(playerCards, score);
                }));
    }

    private void displayGameResult(List<Player> players) {
        Map<String, GameResult> playerGameResults = blackJackGame.calculateGameResults(players);
        List<GameResult> dealerGameResult = createDealerGameResult(playerGameResults);
        outputView.printGameResult(playerGameResults, dealerGameResult);
    }

    private List<GameResult> createDealerGameResult(Map<String, GameResult> playerGameResults) {
        List<GameResult> dealerGameResults = new ArrayList<>();
        playerGameResults.values().forEach(gameResult -> {
            calculateDealerGameResult(gameResult, dealerGameResults);
        });

        return dealerGameResults;
    }

    private static void calculateDealerGameResult(GameResult gameResult, List<GameResult> dealerGameResults) {
        if (gameResult == GameResult.WIN) {
            dealerGameResults.add(GameResult.LOSE);
            return;
        }

        if (gameResult == GameResult.LOSE) {
            dealerGameResults.add(GameResult.WIN);
            return;
        }

        dealerGameResults.add(GameResult.DRAW);
    }
}
