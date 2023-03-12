import domain.game.BlackJackGame;
import domain.game.Referee;
import domain.player.Player;
import domain.strategy.RandomBasedShuffleStrategy;
import view.AddCardCommand;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        new Application().startGame();
    }
    
    public void startGame() {
        BlackJackGame blackJackGame = new BlackJackGame(getParticipantNames(), new RandomBasedShuffleStrategy());
        
        initializedBlackjackGame(blackJackGame);
        settingParticipantsBetAmount(blackJackGame);
        giveCardToPlayers(blackJackGame);
        printProfitResults(blackJackGame, new Referee());
    }
    
    private String getParticipantNames() {
        OutputView.printParticipantNamesGuide();
        return InputView.repeat(InputView::inputParticipantNames);
    }
    
    private void initializedBlackjackGame(BlackJackGame blackJackGame) {
        blackJackGame.giveTwoCardToPlayers();
        OutputView.printPlayersInformation(blackJackGame);
    }
    
    private void settingParticipantsBetAmount(BlackJackGame blackJackGame) {
        blackJackGame.settingBetAmountToParticipantsBy(this::inputBetAmount);
    }
    
    private Double inputBetAmount(String playerName) {
        return InputView.repeat(() -> InputView.inputBetAmount(playerName));
    }
    
    private void giveCardToPlayers(BlackJackGame blackJackGame) {
        blackJackGame.giveCardToParticipantsBy(this::inputCommand, OutputView::printParticipantCardStatus);
        blackJackGame.giveCardToDealerBy(ignore -> OutputView.printGiveDealerCardMessage());
        OutputView.printPlayersFinalInformation(blackJackGame.getPlayers());
    }
    
    private AddCardCommand inputCommand(Player participant) {
        OutputView.printAddCardGuide(participant.getName());
        return InputView.repeat(InputView::inputAddCardCommand);
    }
    
    private void printProfitResults(BlackJackGame blackJackGame, Referee referee) {
        referee.saveBattleResults(blackJackGame);
        OutputView.printPlayersGameResults(blackJackGame, referee);
    }
}
