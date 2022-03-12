package domain;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import view.InputView;

public final class BlackJackGame {
    public void run() {
        initParticipants();
    }

    private void initParticipants() {
        final List<String> names = InputView.inputPlayerName();
//        final Players players = new Player(names);
//        final Dealer dealer = new Dealer();
    }
}
