package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Game {

    public void run() {
        Playing playing = new Playing();
        Result result = new Result();

        Dealer dealer = new Dealer();
        Players players = createPlayers();

        playing.play(dealer, players);

        result.openResult(dealer, players);
        result.win(dealer, players);
    }

    private Players createPlayers() {
        OutputView.printPlayerNameInstruction();
        try {
            return new Players(InputView.inputPlayerName());
        } catch (IllegalArgumentException exception) {
            OutputView.printExceptionMessage(exception.getMessage());
            return createPlayers();
        }
    }
}
