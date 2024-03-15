package blackjack;

import blackjack.domain.Money;
import blackjack.domain.Name;
import blackjack.domain.Players;
import blackjack.view.InputView;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        List<Name> playerNames = inputView.readPlayerNames();
        Players players = Players.from(playerNames);
        Map<Name, Money> playersBetMoney = inputView.readPlayersBetMoney(playerNames);
    }
}
