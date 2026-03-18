package controller;

import java.util.List;
import model.BettingCalculator;
import model.CardDispenser;
import model.Cards;
import model.Dealer;
import model.GameResult;
import model.Player;
import model.Players;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    public void run() {
        Players players = createPlayers();
        BlackJackGame game = createGame(players);
        startGame(game);
        finishGame(game);
    }

    private Players createPlayers() {
        List<String> names = InputView.readPlayerNames();
        return Players.from(names);
    }

    private BlackJackGame createGame(Players players) {
        Dealer dealer = new Dealer();
        CardDispenser dispenser = new CardDispenser(Cards.createShuffledDeck());
        BlackJackRound round = new BlackJackRound(dealer, players, dispenser);
        return new BlackJackGame(round, new BettingCalculator());
    }

    private void startGame(BlackJackGame game) {
        game.prepare();
        printInitialCards(game);
        game.play();
    }

    private void finishGame(BlackJackGame game) {
        printFinalCards(game);
        GameResult result = game.finish();
        printResult(result);
    }

    private void printInitialCards(BlackJackGame game) {
        OutputView.printCardOpen(game.players());
        OutputView.printCardByDealer(game.dealer());
        game.players().players().forEach(OutputView::printCardByPlayer);
        OutputView.printBlank();
    }

    private void printFinalCards(BlackJackGame game) {
        OutputView.printBlank();
        printDealerScore(game.dealer());
        game.players().players().forEach(this::printPlayerScore);
    }

    private void printDealerScore(Dealer dealer) {
        OutputView.printCardByPlayerWithScore(dealer, dealer.calculateTotalScore());
    }

    private void printPlayerScore(Player player) {
        OutputView.printCardByPlayerWithScore(player, player.calculateTotalScore());
    }

    private void printResult(GameResult result) {
        OutputView.printBettingResultHeader();
        OutputView.printBettingResult(result.getParticipantsBettingResults());
    }
}