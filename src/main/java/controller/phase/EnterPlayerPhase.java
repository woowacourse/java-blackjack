package controller.phase;

import controller.GameContext;
import java.util.List;
import model.Player;
import view.InputView;

public class EnterPlayerPhase implements GamePhase {

    public EnterPlayerPhase() {
    }

    @Override
    public void execute(GameContext gameContext) {
        List<String> inputNames = InputView.readPlayerNames();
        List<Player> players = createPlayers(inputNames);
        gameContext.initializePlayers(players);
    }

    private List<Player> createPlayers(List<String> names) {
        return names.stream()
                .map(Player::new)
                .toList();
    }
}
