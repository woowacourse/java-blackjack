package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.betting.BettingAmount;
import blackjack.domain.betting.BettingAreas;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.Result;
import blackjack.util.Retryable;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = generateParticipants();
        BettingAreas bettingAreas = generateBettingAreas(participants.getPlayers());
        BlackJackGame blackJackGame = new BlackJackGame(participants, bettingAreas);

        startGame(blackJackGame);
        playGame(blackJackGame);
        closeGame(blackJackGame);
    }

    private Participants generateParticipants() {
        return Retryable.retryWhenException(() -> new Participants(requestPlayerNames()));
    }

    private List<String> requestPlayerNames() {
        return inputView.readPlayerNames();
    }

    private BettingAreas generateBettingAreas(final List<Player> players){
        Map<Player, BettingAmount> bettingAreas = new HashMap<>();

        players.forEach(player ->
            bettingAreas.put(player, generateBettingAmount(player.getName()))
        );

        return new BettingAreas(bettingAreas);
    }

    private BettingAmount generateBettingAmount(String playerName){
        return Retryable.retryWhenException(() -> new BettingAmount(requestBettingAmount(playerName)));
    }

    private int requestBettingAmount(String playerName){
        return inputView.readBetAmount(playerName);
    }

    private void startGame(final BlackJackGame blackJackGame) {
        blackJackGame.drawInitialCards();
        showInitialCards(blackJackGame.getParticipants());
    }

    private void showInitialCards(final Participants participants) {
        showInitialCardMessage(participants.getPlayers());
        showInitialDealerCard(participants.getDealer());
        showInitialPlayersCards(participants.getPlayers());
    }

    private void showInitialCardMessage(final List<Player> players) {
        final List<String> playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());

        outputView.printInitialCardMessage(playerNames);
    }

    private void showInitialDealerCard(final Dealer dealer) {
        outputView.printInitialPlayerCards(dealer.getName(), dealer.getOpenCardName());
    }

    private void showInitialPlayersCards(final List<Player> players) {
        players.forEach(player ->
                outputView.printInitialPlayerCards(player.getName(), player.getCardNames()));
    }

    private void playGame(final BlackJackGame blackJackGame) {
        playPlayersTurn(blackJackGame);
        playDealerTurn(blackJackGame);
    }

    private void playPlayersTurn(final BlackJackGame blackJackGame) {
        blackJackGame.getParticipants()
                .getPlayers()
                .forEach(player ->
                        playEachPlayerTurn(blackJackGame, player));
    }

    private void playEachPlayerTurn(final BlackJackGame blackJackGame, final Player player) {
        final String playerName = player.getName();
        while (player.isAbleToReceive() && requestIsHit(playerName)) {
            blackJackGame.drawNewCard(player);
            showEachPlayerCards(player);
        }
    }

    private boolean requestIsHit(final String playerName) {
        return Retryable.retryWhenException(() -> inputView.readIsHit(playerName));
    }

    private void showEachPlayerCards(final Player player) {
        outputView.printInitialPlayerCards(player.getName(), player.getCardNames());
    }

    private void playDealerTurn(final BlackJackGame blackJackGame) {
        final Dealer dealer = blackJackGame.getParticipants().getDealer();
        while (dealer.isAbleToReceive()) {
            blackJackGame.drawNewCard(dealer);
            outputView.printDealerReceived();
        }
    }

    private void closeGame(final BlackJackGame blackJackGame) {
        showFinalCards(blackJackGame.getParticipants());

        final GameResult gameResult = blackJackGame.getGameResult();
        showDealerResult(gameResult.getDealerResults());
        showPlayersResult(gameResult, blackJackGame.getParticipants().getPlayers());
    }

    private void showFinalCards(final Participants participants) {
        participants.getAllParticipants().forEach(participant ->
                outputView.printFinalCards(participant.getName(), participant.getCardNames(), participant.getScore()));
    }

    private void showDealerResult(final Map<Result, Integer> dealerResults) {
        Map<String, Integer> convertedDealerResult = dealerResults.keySet().stream()
                .collect(Collectors.toMap(
                        Result::getTerm,
                        dealerResults::get
                ));

        outputView.printDealerResults(convertedDealerResult);
    }

    private void showPlayersResult(final GameResult gameResult, final List<Player> players) {
        players.forEach(
                player -> showEachPlayerResult(player, gameResult.getPlayerResult(player)));
    }

    private void showEachPlayerResult(final Player player, final Result playerResult) {
        outputView.printPlayerResult(player.getName(), playerResult.getTerm());
    }
}
