package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.FinalIncome;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        List<String> names = inputView.readNames();
        Players players = new Players(names);
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        inputBetAmout(players);

        BlackJackGame blackJackGame = new BlackJackGame(players, dealer, deck);
        blackJackGame.initDraw();

        outputView.printInitDraw(players, dealer);

        playerTurn(players, deck);
        dealerTurn(dealer, deck);

        outputView.printFinalCardResult(dealer, players);

        FinalIncome result = blackJackGame.judgeGameResult();
        outputView.printFinalGameResult(result);
    }

    private void inputBetAmout(Players players) {
        for (Player player : players.getPlayers()) {
            String name = player.getName();
            int amount = inputView.readBetAmount(name);
            player.setBetAmount(amount);
        }
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (dealer.shouldDraw()) {
            outputView.printDealerDraw();
            dealer.recieveCard(deck.draw());
        }
    }

    private void playerTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            while (player.shouldDraw()) {
                boolean isHit = inputView.readHitAnswer(player.getName());
                if (isHit) {
                    player.recieveCard(deck.draw());
                }

                outputView.printCard(player);

                if (!isHit) {
                    break;
                }
            }
        }
    }
}
