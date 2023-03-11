import domain.game.BlackJackGame;
import domain.game.Referee;
import domain.player.Player;
import domain.strategy.RandomBasedShuffleStrategy;
import view.AddCardCommand;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        new Application().startGame();
    }
    
    public void startGame() {
        BlackJackGame blackJackGame = new BlackJackGame(getParticipantNames(), new RandomBasedShuffleStrategy());
        initializedBlackjackGame(blackJackGame);
    
        Referee referee = new Referee();
        // TODO : 배팅 금액 입력 및 referee에 저장 기능 구현
        
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
    
    private void giveCardToPlayers(BlackJackGame blackJackGame) {
        giveCardToParticipants(blackJackGame);
        giveCardToDealer(blackJackGame);
        OutputView.printPlayersFinalInformation(blackJackGame.getPlayers());
    }
    
    private void giveCardToParticipants(BlackJackGame blackJackGame) {
        List<Player> participants = blackJackGame.getParticipants();
        for (Player participant : participants) {
            giveCardToParticipant(blackJackGame, participant);
        }
    }
    
    private void giveCardToParticipant(BlackJackGame blackJackGame, Player participant) {
        AddCardCommand command = getCommand(participant);
        if (command.isAddCardCommand()) {
            blackJackGame.giveCard(participant);
        }
        OutputView.printParticipantCardCondition(List.of(participant));
        
        if (command.isNotAddCardCommand() || participant.isFinished()) {
            stopGivingCard(participant, command);
            return;
        }
        giveCardToParticipant(blackJackGame, participant);
    }
    
    private AddCardCommand getCommand(Player participant) {
        OutputView.printAddCardGuide(participant.getName());
        return InputView.repeat(InputView::inputAddCardCommand);
    }
    
    private void stopGivingCard(Player participant, AddCardCommand command) {
        if (command.isNotAddCardCommand()) {
            participant.drawStop();
            return;
        }
        
        if (participant.isFinished()) {
            OutputView.printBustMessage(participant.getName());
        }
    }
    
    private void giveCardToDealer(BlackJackGame blackJackGame) {
        if (blackJackGame.shouldDealerGetCard()) {
            blackJackGame.giveDealerCard();
            OutputView.printGiveDealerCardMessage();
        }
    }
}
