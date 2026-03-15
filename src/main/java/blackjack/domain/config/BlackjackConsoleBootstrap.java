package blackjack.domain.config;

import blackjack.BlackjackApplication;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participants.Bet;
import blackjack.domain.participants.Name;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.PlayerGroup;
import blackjack.domain.participants.PlayerNames;
import blackjack.view.BlackjackView;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackConsoleBootstrap {
    public static BlackjackApplication createApplication() {
        BlackjackView view = new BlackjackView(new InputView(), new OutputView());
        List<Player> players = readPlayers(view);

        BlackjackGame game = BlackjackGame.createBasic(new PlayerGroup(players));
        return new BlackjackApplication(view, game);
    }

    private static List<Player> readPlayers(BlackjackView view) {
        PlayerNames playerNames = PlayerNames.from(view.readPlayers());
        return playerNames.names().stream()
            .map(name -> initPlayer(name, view.readBetAmount(name.getValue())))
            .toList();
    }

    private static Player initPlayer(Name name, long betAmount) {
        return Player.createEmptyHand(name, new Bet(betAmount));
    }
}
