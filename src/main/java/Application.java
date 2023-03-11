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
    
        Referee referee = new Referee();
        
        settingParticipantsBetAmount(blackJackGame, referee);
        giveCardToPlayers(blackJackGame);
        referee.saveBattleResults(blackJackGame);
        
        OutputView.printPlayersGameResults(blackJackGame, referee);
    }
    
    private String getParticipantNames() {
        OutputView.printParticipantNamesGuide();
        return InputView.repeat(InputView::inputParticipantNames);
    }
    
    private void initializedBlackjackGame(BlackJackGame blackJackGame) {
        blackJackGame.giveTwoCardToPlayers();
        OutputView.printPlayersInformation(blackJackGame);
    }
    
    private void settingParticipantsBetAmount(BlackJackGame blackJackGame, Referee referee) {
        blackJackGame.settingBetAmountToParticipantsBy(
                OutputView::printParticipantBetAmountInputGuide,
                this::inputBetAmount,
                referee::saveParticipantBetAmount
        );
    }
    
    private Double inputBetAmount() {
        return InputView.repeat(InputView::inputBetAmount);
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
}
