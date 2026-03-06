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





    }
}
