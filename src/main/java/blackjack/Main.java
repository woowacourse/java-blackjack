package blackjack;

import blackjack.domain.CardFactory;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Game;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> playerNames = InputView.readName();
        CardFactory cardFactory = new CardFactory();
        Game game = new Game(new Dealer(new Deck(cardFactory.createBlackJackCard())), Players.convertTo(playerNames));

        game.initializeHand();

        List<Player> players = game.getPlayers();
        Dealer dealer = game.getDealer();
        OutputView.printInitialHand(dealer, players);

        playerTurn(players, dealer);
        // 딜러 턴 구현
        while (dealer.canHit()) {
            dealer.putCard(dealer.draw());
            //딜러는 16이하라 한장의 카드를 더 받았습니다.
            OutputView.printDealerDraw(dealer);
        }
        OutputView.printDealerStand(dealer);

//         게임에서 결과 반환
//        결과 출력
//        딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
//        pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
//        jason카드: 7클로버, K스페이드 - 결과: 17

//        ## 최종 승패
//        딜러: 1승 1패
//        pobi: 승
//        jason: 패
        OutputView.printResult(game.calculateResult(), dealer);
    }

    private static void playerTurn(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            while (player.canHit() && InputView.readHitOrStand(player)) {
                player.putCard(dealer.draw());
                if (player.canHit()) {
                    OutputView.printTotalHand(player);
                } else {
                    // TODO: 터졌을 떄 안내
                }
            }
            OutputView.printTotalHand(player);
        }
    }

}
