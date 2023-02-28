import java.util.List;
import java.util.stream.Collectors;
import view.InputView;

public class BlackjackController {
    private final InputView inputView;

    public BlackjackController(){
        inputView = new InputView();
    }

    public void run(){
        Players players = createPlayers();

    }

    private Players createPlayers(){
        List<Player> players = readNames().stream()
                .map(PlayerName::new)
                .map(Player::new)
                .collect(Collectors.toList());

        return new Players(players);
    }

    private List<String> readNames(){
        return inputView.requestPlayerNames();
    }
}
