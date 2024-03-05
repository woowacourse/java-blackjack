import domain.Name;
import domain.Participants;
import domain.Player;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackjackGame {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        List<String> names = inputView.readNames();
        List<Player> players = names.stream()
                .map(name -> new Player(new Name(name)))
                .toList();
        Player dealer = new Player(new Name("딜러"));
        Participants participants = new Participants(dealer, players);
        participants.initialSetting();
        outputView.printSetting(dealer, players);

        players.forEach((player) -> {
            while (player.isNotOver(21) && inputView.readCommand(player.getName().getName())) {
                participants.addCard(player);
                outputView.printCurrentCard(player);
                outputView.printNewLine();
            }

        });

        while (dealer.isNotOver(17)) {
            participants.addCard(dealer);
            outputView.printDealerOneMoreCard();
        }

        outputView.printResult(dealer, players);
    }
}
