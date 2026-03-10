package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;

public class BlackJackController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        List<String> names = inputView.readNames();
        Players players = new Players(names);
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        BlackJackGame blackJackGame = new BlackJackGame(players, dealer, deck);
        blackJackGame.initDraw();

        outputView.printInitDraw(players, dealer);

        playerTurn(players, deck);

        dealerTurn(dealer, deck);

        outputView.printFinalCardResult(dealer, players);

        HashMap<Player, GameResult> result = blackJackGame.judgeGameResult();
        outputView.printFinalGameResult(result);


    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (true) {
            boolean isOver17 = dealer.isOver17();
            if (isOver17) {
                break;
            }
            outputView.printDealerDraw();
            dealer.recieveCard(deck.draw());
        }
    }

    private void playerTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            while (true) {
                boolean isBust = player.isBust();
                if (isBust) {
                    break;
                }
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
