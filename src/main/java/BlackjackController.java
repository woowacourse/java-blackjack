import java.util.List;
import java.util.stream.Collectors;
import view.InputView;

// TODO: 2023/02/28 결과 계산하기
// TODO: 2023/02/28 출력
public class BlackjackController {
    private final InputView inputView;

    public BlackjackController() {
        inputView = new InputView();
    }

    public void run() {
        Players players = createPlayers();
        BlackjackGame blackjackGame = new BlackjackGame(players);
        blackjackGame.giveInitCards();

        for (Player player : players.getPlayers()) {
            requestMoreCard(blackjackGame, player);
        }
        blackjackGame.giveAdditionalCardToDealer();
        blackjackGame.result();
    }

    private Players createPlayers() {
        return new Players(readNames().stream()
                .map(PlayerName::new)
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    private List<String> readNames() {
        return inputView.requestPlayerNames();
    }

    private void requestMoreCard(BlackjackGame blackjackGame, Player player) {
        while (isHitCommand(player.getName()) && !player.isBusted()) {
            blackjackGame.giveCardTo(player);
        }
    }

    private boolean isHitCommand(String name) {
        return Command.from(inputView.requestMoreCard(name)) != Command.HOLD;
    }
}
