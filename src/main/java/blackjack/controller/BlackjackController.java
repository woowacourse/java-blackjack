package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.domain.PlayerCardsName;
import blackjack.domain.PlayerFinalCardsScore;
import blackjack.domain.PlayerFinalResult;
import blackjack.domain.ScoreCompareResult;
import blackjack.service.CardDistributor;
import blackjack.service.Game;
import blackjack.utils.InputParser;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {
    private final CardDistributor cardDistributor;
    private final Game game;

    public BlackjackController(CardDistributor cardDistributor) {
        this.cardDistributor = cardDistributor;
        this.game = new Game(cardDistributor);
    }

    public void startGame() {
        List<String> playerNames = getPlayerNames();
        List<Player> players = getPlayers(playerNames);
        Dealer dealer = new Dealer();

        setupInitialHand(players, dealer, playerNames); // 2장씩 카드 분배

        processTurn(players); // 플레이어 hit or stand
        playDealerTurn(dealer);

        calculateFinalScore(players, dealer);
        calculateFinalGameResult(players, dealer);
    }

    private void setupInitialHand(List<Player> players, Dealer dealer, List<String> playerNames) {
        distributeInitialCards(players, dealer);
        OutputView.printInitialCardsDistribution(playerNames);
        printInitialCards(players, dealer);
    }

    private static List<String> getPlayerNames() {
        String playerNamesStr = InputView.askPlayerNames();
        List<String> playerNames = InputParser.splitPlayerNames(playerNamesStr);
        return playerNames;
    }

    private void calculateFinalGameResult(List<Player> players, Dealer dealer) {
        GameResult gameResult = game.judgeTotalGameResult(players, dealer);
        Map<ScoreCompareResult, Integer> dealerResult = gameResult.dealerResult();

        LinkedHashMap<Player, ScoreCompareResult> playerResult = gameResult.playerResults();
        List<PlayerFinalResult> playerNameResult = new ArrayList<>();
        for (Entry<Player, ScoreCompareResult> entry : playerResult.entrySet()) {
            playerNameResult.add(new PlayerFinalResult(entry.getKey().getName(), entry.getValue()));
        }
        OutputView.printFinalResult(dealerResult, playerNameResult);
    }

    private void playDealerTurn(Dealer dealer) {
        game.dealerDrawsCardsUntilDone(dealer);
        OutputView.printDealerCardDrawnResult(dealer.getAdditionalDrawnCardCount());
    }

    private void processTurn(List<Player> players) {
        for (Player player : players) {
            processPlayerTurn(player);
        }
    }

    private void processPlayerTurn(Player player) {
        while (canContinueTurn(player)) {
            drawCard(player);
        }
    }

    private boolean canContinueTurn(Player player) {
        String hitOrStand = InputView.askHitOrStand(player.getName());
        return isHit(hitOrStand) && !player.isBust();
    }

    private void drawCard(Player player) {
        cardDistributor.distributeCardToPlayer(player);
        OutputView.printDrawnCards(player.getName(), player.getCardNames());
    }

    private static void printInitialCards(List<Player> players, Dealer dealer) {
        List<PlayerCardsName> playersCardsName = new ArrayList<>();
        for (Player player : players) {
            playersCardsName.add(new PlayerCardsName(player.getName(), player.getCardNames()));
        }
        OutputView.printAllUserCards(playersCardsName, dealer.getOneCardName());
    }

    private List<Player> getPlayers(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        return players;
    }

    private void calculateFinalScore(List<Player> players, Dealer dealer) {
        List<PlayerFinalCardsScore> playerFinalCardsScores = new ArrayList<>();
        for (Player player : players) {
            playerFinalCardsScores.add(
                    new PlayerFinalCardsScore(player.getName(), player.getCardNames(), player.calculateTotalScore()));
        }

        List<String> dealerCards = dealer.getCardNames();
        int dealerScore = dealer.calculateTotalScore();

        OutputView.printFinalCardScores(playerFinalCardsScores, dealerCards, dealerScore);
    }

    private void distributeInitialCards(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            cardDistributor.distributeTwoCardsToPlayer(player);
        }
        cardDistributor.distributeTwoCardsToDealer(dealer);
    }

    private boolean isHit(String hitOrStand) {
        if (hitOrStand.equals("y")) {
            return true;
        }
        if (hitOrStand.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("잘못된 입력입니다. y 또는 n을 입력해주세요");
    }

}
