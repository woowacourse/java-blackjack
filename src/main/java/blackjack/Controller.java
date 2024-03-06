package blackjack;

import java.util.List;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> playerNames = inputView.readPlayerNames();

        List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();

        Dealer dealer = new Dealer();
    }
}
