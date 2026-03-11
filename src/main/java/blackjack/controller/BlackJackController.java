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
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {
        List<String> names = inputView.readNames();
        Players players = new Players(names);
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        BlackJackGame blackJackGame = new BlackJackGame(players, dealer, deck);
        blackJackGame.initDraw();

        // 출력
        outputView.printInitDraw(players, dealer);

        // 플레이어 턴
        playerTurn(players, deck);

        // 딜러 턴
        dealerTurn(dealer, deck);

        //최종 결과 출력
        outputView.printFinalCardResult(dealer, players);

        //  최종 승패 출력
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
            dealer.receiveCard(deck.draw());
        }
    }

    private void playerTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            while (true) {
                // 버스트인지 확인
                boolean isBust = player.isBust();
                if (isBust) {
                    break;
                }
                boolean isHit = inputView.readHitAnswer(player.getName());
                if (isHit) {
                    player.receiveCard(deck.draw());
                }

                // 출력
                outputView.printCard(player);

                if (!isHit) {
                    break;
                }
            }
        }
    }
}
