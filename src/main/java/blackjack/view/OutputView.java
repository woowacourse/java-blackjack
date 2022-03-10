package blackjack.view;

import blackjack.model.Card;
import blackjack.model.Dealer;
import blackjack.model.Player;
import java.util.List;

public class OutputView {
    private OutputView() {}

    public static void printOpenCard(Dealer dealer, List<Player> gamers) {
        System.out.println("dealer.name() = " + dealer.name());
        for (Player gamer : gamers) {
            System.out.println("gamer.name() = " + gamer.name());
            for (Card card : gamer.openCards()) {

            }
        }

    }
}
