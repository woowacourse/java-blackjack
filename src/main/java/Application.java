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
        giveCardOrFinishToParticipants(blackJackGame);
        giveCardToDealer(blackJackGame);
        OutputView.printPlayersFinalInformation(blackJackGame.getPlayers());
    }
    
    private void giveCardOrFinishToParticipants(BlackJackGame blackJackGame) {
        List<Player> participants = blackJackGame.getParticipants();
        for (Player participant : participants) {
            decideGiveOrFinish(blackJackGame, participant);
        }
    }
    
    private void decideGiveOrFinish(BlackJackGame blackJackGame, Player participant) {
        if (participant.isFinished()) {
            OutputView.printFinishedMessage(participant.getName());
            OutputView.printParticipantCardCondition(List.of(participant));
            return;
        }
        
        giveCardToParticipant(blackJackGame, participant);
    }
    
    private void giveCardToParticipant(BlackJackGame blackJackGame, Player participant) {
        AddCardCommand command = getCommand(participant);
        if (command.isAddCardCommand()) {
            blackJackGame.giveCard(participant);
        }
        OutputView.printParticipantCardCondition(List.of(participant));
    
        if (command.isNotAddCardCommand() || participant.isFinished()) {
            finishGiveCard(participant, command);
            return;
        }
        giveCardToParticipant(blackJackGame, participant);
    }
    
    private void finishGiveCard(Player participant, AddCardCommand command) {
        if (command.isNotAddCardCommand()) {
            participant.drawStop();
            return;
        }
        
        OutputView.printFinishedMessage(participant.getName());
    }
    
    private AddCardCommand getCommand(Player participant) {
        OutputView.printAddCardGuide(participant.getName());
        return InputView.repeat(InputView::inputAddCardCommand);
    }
    
    private void giveCardToDealer(BlackJackGame blackJackGame) {
        if (blackJackGame.isDealerFinished()) {
            return;
        }
        
        blackJackGame.giveCard(blackJackGame.getDealer());
        OutputView.printGiveDealerCardMessage();
    
        if (isDealerNotFinished(blackJackGame)) {
            blackJackGame.dealerDrawStop();
        }
    }
    
    private boolean isDealerNotFinished(BlackJackGame blackJackGame) {
        return !blackJackGame.isDealerFinished();
    }
}
