package blackjack;

import blackjack.controller.Controller;
import blackjack.domain.user.component.BettingAmount;
import blackjack.domain.card.CardDeckFactory;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.component.Name;
import blackjack.domain.user.Players;
import blackjack.view.InputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Dealer dealer = new Dealer();

        List<Name> names = InputView.getNames();
        List<BettingAmount> bettingAmounts = InputView.getBettingAmounts(names);
        Map<Name, BettingAmount> playerInfo = IntStream.range(0, names.size())
                .boxed()
                .collect(Collectors.toMap(names::get, bettingAmounts::get));
        Players players = new Players(playerInfo);
        Controller controller = new Controller(dealer, players, CardDeckFactory.create());

        controller.play();
        controller.computeResult();
    }
}
