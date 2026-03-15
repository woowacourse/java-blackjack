package blackjack.controller;

import blackjack.domain.deck.Deck;
import blackjack.domain.game.BlackJackGame;
import blackjack.domain.game.FinalIncome;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Players players = inputView.readPlayers();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        BlackJackGame blackJackGame = startGame(players, dealer, deck);
        playGame(players, dealer, deck);
        showGameResult(blackJackGame, dealer, players);
    }

    private BlackJackGame startGame(Players players, Dealer dealer, Deck deck) {
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer, deck);
        blackJackGame.initDraw();
        outputView.printInitDraw(players, dealer);
        return blackJackGame;
    }

    private void playGame(Players players, Dealer dealer, Deck deck) {
        playerTurn(players, deck);
        dealerTurn(dealer, deck);
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (dealer.shouldDraw()) {
            outputView.printDealerDraw();
            dealer.recieveCard(deck.draw());
        }
    }

    private void playerTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            while (player.shouldDraw() && inputView.readHitAnswer(player.getName())) {
                player.recieveCard(deck.draw());
                outputView.printCard(player);
            }
            if (!player.isBust()) {
                outputView.printCard(player);
            }
        }
    }

    private void showGameResult(BlackJackGame blackJackGame, Dealer dealer, Players players) {
        outputView.printFinalCardResult(dealer, players);
        FinalIncome result = blackJackGame.judgeGameResult();
        outputView.printFinalGameResult(result);
    }

}
