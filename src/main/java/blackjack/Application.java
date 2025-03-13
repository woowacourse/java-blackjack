package blackjack;

import blackjack.cardMachine.CardRandomMachine;
import blackjack.gamer.Dealer;
import blackjack.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.ResultView;

public class Application {
    public static void main(String[] args) {
        startBlackjack();
    }

    public static void startBlackjack() {
        final Blackjack blackjack = new Blackjack(new Dealer(new CardRandomMachine()), new Players());
        final InputView inputView = new InputView();
        final ResultView resultView = new ResultView();

        blackjack.initDealer();
        blackjack.makePlayers(inputView);
        blackjack.betMoney(inputView);
        blackjack.deal();
        blackjack.showInitialCards(resultView);
        if (!blackjack.isPush()) {
            // 플레이어와 딜러 카드 나눠주기
        }
    }
}
