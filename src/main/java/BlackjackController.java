import java.util.List;
import java.util.stream.Collectors;
import view.InputView;

public class BlackjackController {
    private final InputView inputView;

    public BlackjackController() {
        inputView = new InputView();
    }

    public void run() {
        List<Player> players = createPlayers();
        BlackjackGame blackjackGame = new BlackjackGame(players);
        blackjackGame.giveInitCards();

        for (Player player : players) {
            requestMoreCard(blackjackGame, player);
        }
    }

    private List<Player> createPlayers() {
        return readNames().stream()
                .map(PlayerName::new)
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private List<String> readNames() {
        return inputView.requestPlayerNames();
    }

    private void requestMoreCard(BlackjackGame blackjackGame, Player player) {
        while (isHitCommand(player.getName())) {
            blackjackGame.giveCardTo(player);
        }
    }

    private boolean isHitCommand(String name) {
        return Command.from(inputView.requestMoreCard(name)) != Command.HOLD;
    }
}
