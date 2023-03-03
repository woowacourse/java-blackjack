import domain.BlackJack;
import domain.RandomCardIndexGenerator;
import domain.Users;
import domain.user.Player;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private BlackJack blackJack;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ready();
        play();
    }

    private void ready() {
        List<String> playerNames = inputView.askPlayerNames();
        Users users = Users.from(playerNames);
        blackJack = BlackJack.of(users, new RandomCardIndexGenerator());
        outputView.printInitMessage(playerNames);
        outputView.printDealerCardWithHidden(blackJack.getDealerCardWithHidden());
        outputView.printPlayerCards(blackJack.getPlayerToCard());
    }

    private void play() {
        List<Player> hittablePlayers = blackJack.getHittablePlayers();
        for (Player player : hittablePlayers) {
            askPlayerHitCommand(player);
        }
        giveCardToDealer();
    }

    private void askPlayerHitCommand(final Player player) {
        while (player.isHittable()) {
            String playerName = player.getName();
            String command = inputView.askHitCommand(playerName);
            HitCommand hitCommand = HitCommand.from(command);
            giveCardToHittable(playerName, hitCommand);
        }
    }

    private void giveCardToHittable(final String playerName, final HitCommand hitCommand) {
        if (hitCommand.isHit()) {
            blackJack.giveCard(playerName);
        }
    }

    private void giveCardToDealer() {
        while (blackJack.isDealerHittable()) {
            blackJack.giveCardToDealer();
            outputView.printDealerHitMessage();
        }
    }
}
