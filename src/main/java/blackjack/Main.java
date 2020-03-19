package blackjack;

import blackjack.controller.Controller;
import blackjack.domain.card.BettingAmount;
import blackjack.domain.card.CardDeckFactory;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.Players;
import blackjack.view.InputView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Dealer dealer = new Dealer();

        List<Name> names = InputView.getNames();
        List<BettingAmount> bettingAmounts = InputView.getBettingAmounts(names);
        Players players = new Players(names, bettingAmounts);
        Controller controller = new Controller(dealer, players, CardDeckFactory.create());

        controller.play();
        controller.computeResult();
    }
}
