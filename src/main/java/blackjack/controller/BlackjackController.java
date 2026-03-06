package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Player;
import blackjack.domain.Status;
import blackjack.domain.Trump;
import blackjack.utils.Parser;
import blackjack.utils.RetryExecutor;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    public void run() {
        Trump trump = new Trump();
        List<Player> players = RetryExecutor.retry(this::readNicknames);
        Dealer dealer = new Dealer(new Hand(), Status.HIT, trump);
        dealer.pitch(players);
        OutputView.printStartMessage(players, dealer);
    }

    private List<Player> readNicknames() {
        String rawNicknames = InputView.readNicknames();
        List<String> nicknames = Parser.parseNickname(rawNicknames);
        return nicknames.stream()
            .map(nickname ->
                new Player(new Hand(), Status.HIT, nickname))
            .toList();
    }


}
