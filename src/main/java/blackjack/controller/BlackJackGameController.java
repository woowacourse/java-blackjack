package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.Result;
import blackjack.view.InputView;
import blackjack.view.OutputView;

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
        BlackJackGame blackJackGame = new BlackJackGame(requestPlayerNames());
        blackJackGame.drawInitialCards();
        showInitialCards(blackJackGame.getParticipants());

        playTurn(blackJackGame);
        closeGame(blackJackGame);
    }

    private List<String> requestPlayerNames() {
        outputView.printRequestPlayerNames();
        return inputView.readPlayerNames();
    }

    private void showInitialCards(final Participants participants) {
        List<String> playerNames = participants.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());

        outputView.printInitialCardMessage(playerNames);
        outputView.printInitialPlayerCards(participants.getDealer().getName(), participants.getDealer().getOpenCardName());
        participants.getPlayers().forEach(player ->
                outputView.printInitialPlayerCards(player.getName(), player.getCardNames()));
    }

    private void playTurn(final BlackJackGame blackJackGame) {
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
        while (player.isAbleToReceive() && inputView.readIsHit(player.getName())) {
            blackJackGame.drawNewCard(player);
            showEachPlayerCards(player);
        }
    }

    private void showEachPlayerCards(final Player player) {
        outputView.printInitialPlayerCards(player.getName(), player.getCardNames());
    }

    private void playDealerTurn(final BlackJackGame blackJackGame) {
        Dealer dealer = blackJackGame.getParticipants().getDealer();
        while (dealer.isAbleToReceive()) {
            blackJackGame.drawNewCard(dealer);
            outputView.printDealerReceived();
        }
    }

    private void closeGame(final BlackJackGame blackJackGame) {
        showFinalCards(blackJackGame.getParticipants());

        GameResult gameResult = blackJackGame.getGameResult();

        showDealerResult(gameResult.getDealerResults());
        blackJackGame.getParticipants().getPlayers().forEach(
                player -> showPlayerResult(player, gameResult.getPlayerResult(player)));
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

    private void showPlayerResult(final Player player, final Result playerResult) {
        outputView.printPlayerResult(player.getName(), playerResult.getTerm());
    }
}
