import domain.BlackJack;
import domain.HitCommand;
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
        try {
            ready();
            play();
            end();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
        }
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
        HitCommand hitCommand = HitCommand.Y;
        while (player.isHittable() && hitCommand.isHit()) {
            String playerName = player.getName();
            String command = inputView.askHitCommand(playerName);
            hitCommand = HitCommand.from(command);
            giveCardToHittable(playerName, hitCommand);
            outputView.printEachPlayerCards(playerName, player.getCards());
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

    private void end() {
        outputView.printDealerCardWithScore(blackJack.getDealerCards(), blackJack.getDealerScore());
        outputView.printPlayerCardWithScore(blackJack.getPlayerToCard(), blackJack.getPlayerToScore());
        outputView.printGameResult(blackJack.calculateDealerResult(), blackJack.calculatePlayerResults());
    }
}
