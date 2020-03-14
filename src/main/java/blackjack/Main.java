package blackjack;

import blackjack.controller.Controller;
import blackjack.domain.card.CardDeckFactory;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;
import blackjack.view.InputView;

public class Main {
    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        Players players = new Players(InputView.getNames());
        Controller controller = new Controller(dealer, players, CardDeckFactory.create());

        controller.play();
        controller.computeResult();
    }
}
