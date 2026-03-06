package blackjack;

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
        outputView.printInitDraw(players,dealer);

        // 플레이어 턴
        for(Player player:players.getPlayers()) {
            while(true) {
                // 버스트인지 확인
                boolean isBust = player.isBust();
                if(isBust) break;
                boolean isHit = inputView.readHitAnswer(player.getName());
                if(isHit) {
                    player.recieveCard(deck.draw());
                }

                // 출력
                outputView.printCard(player);

                if(!isHit) {
                    break;
                }
            }
        }


        // 딜러 턴



    }
}
