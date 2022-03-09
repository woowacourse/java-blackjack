package blackjack;

import blackjack.domain.Dealer;
import blackjack.domain.Players;
import blackjack.view.InputView;

public class Main {
    public static void main(String[] args) {
        Players players = Players.fromNames(InputView.inputPlayerName());
        Dealer dealer = Dealer.init();
    }
}
