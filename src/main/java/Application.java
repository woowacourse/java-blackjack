import io.ConsoleInput;
import io.ConsoleOutput;

public class Application {
    public static void main(String[] args) {
        ConsoleInput input = new ConsoleInput();
        ConsoleOutput output = new ConsoleOutput();

        BlackJackManager manager = new BlackJackManager(
                input.readParticipantsNames(),
                input::readPlayerBetAmounts
                );

        output.printInitialGameSettings(manager.getPlayers(), manager.getDealer());

        manager.performPlayerTurn(
                player -> input.readShouldHit(player.getNickname()),
                output::printPlayerCards
        );

        manager.performDealerTurn(
                output::printDealerHitMessage
        );

        output.printGameResults(manager.getPlayers(), manager.getDealer());
        output.printFinalProfit(manager.getBetCenter(), manager.getDealer());
    }
}
