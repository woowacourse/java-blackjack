import domain.BlackJack;
import domain.HitCommand;
import domain.Users;
import domain.deck.RandomDeckGenerator;
import domain.user.Player;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Controller {
    private BlackJack blackJack;

    public void run() {
        try {
            ready();
            play();
            end();
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void ready() {
        List<String> playerNames = InputView.askPlayerNames();
        Users users = Users.from(playerNames);
        blackJack = BlackJack.of(users, new RandomDeckGenerator().generateDeck());
        OutputView.printInitMessage(playerNames);
        OutputView.printDealerCardWithHidden(blackJack.getDealerCardWithHidden());
        OutputView.printPlayerCards(blackJack.getPlayerToCard());
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
            String command = InputView.askHitCommand(playerName);
            hitCommand = HitCommand.from(command);
            giveCardToHittable(playerName, hitCommand);
            OutputView.printEachPlayerCards(playerName, player.getCards());
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
            OutputView.printDealerHitMessage();
        }
    }

    private void end() {
        OutputView.printDealerCardWithScore(blackJack.getDealerCards(), blackJack.getDealerScore());
        OutputView.printPlayerCardWithScore(blackJack.getPlayerToCard(), blackJack.getPlayerToScore());
        OutputView.printGameResult(blackJack.calculateDealerResult(), blackJack.calculatePlayerResults());
    }
}
