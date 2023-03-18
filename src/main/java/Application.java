import domain.game.BlackJackGame;
import domain.game.Referee;
import domain.player.Player;
import domain.strategy.RandomBasedShuffleStrategy;
import domain.game.AddCardCommand;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        BlackJackGame blackJackGame = new BlackJackGame(getParticipantNames(), new RandomBasedShuffleStrategy());
    
        initializedBlackjackGame(blackJackGame);
        betParticipants(blackJackGame);
        giveCardToPlayers(blackJackGame);
        printProfitResults(blackJackGame, new Referee());
    }
    
    private static String getParticipantNames() {
        return InputView.repeat(InputView::inputParticipantNames);
    }
    
    private static void initializedBlackjackGame(BlackJackGame blackJackGame) {
        blackJackGame.giveTwoCardToPlayers();
        OutputView.printPlayersInformation(blackJackGame.players());
    }
    
    private static void betParticipants(BlackJackGame blackJackGame) {
        blackJackGame.settingBetAmountToParticipantsBy(Application::inputBetAmount);
    }
    
    private static Double inputBetAmount(String playerName) {
        return InputView.repeat(() -> InputView.inputBetAmount(playerName));
    }
    
    private static void giveCardToPlayers(BlackJackGame blackJackGame) {
        blackJackGame.giveCardToParticipantsBy(Application::inputCommand, OutputView::printParticipantCardStatus);
        blackJackGame.giveCardToDealerBy(ignore -> OutputView.printGiveDealerCardMessage());
        OutputView.printPlayersFinalInformation(blackJackGame.players());
    }
    
    private static AddCardCommand inputCommand(Player participant) {
        return InputView.repeat(() -> InputView.inputAddCardCommand(participant.getName()));
    }
    
    private static void printProfitResults(BlackJackGame blackJackGame, Referee referee) {
        referee.saveBattleResults(blackJackGame);
        OutputView.printPlayersGameResults(blackJackGame.players(), referee);
    }
}
